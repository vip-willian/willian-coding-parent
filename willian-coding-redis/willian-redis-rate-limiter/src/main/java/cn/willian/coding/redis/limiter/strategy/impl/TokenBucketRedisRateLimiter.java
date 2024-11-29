package cn.willian.coding.redis.limiter.strategy.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import cn.willian.coding.redis.limiter.annotation.Limiter;
import cn.willian.coding.redis.limiter.enums.LimitWayEnum;
import cn.willian.coding.redis.limiter.strategy.AbstractRedisRateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * 令牌桶限流
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 11:59:57
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class TokenBucketRedisRateLimiter extends AbstractRedisRateLimiter {

    protected DefaultRedisScript<List> redisScript;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisScript = new DefaultRedisScript<>();
        redisScript
            .setScriptSource(new ResourceScriptSource(new ClassPathResource("redis-token-bucket-rate-limiter.lua")));
        redisScript.setResultType(List.class);
    }

    @Override
    public LimitWayEnum getLimitWay() {
        return LimitWayEnum.TOKEN_BUCKET;
    }

    @Override
    public boolean tryAcquire(String key, Limiter limiter) {

        List<Long> rateLimitResponse = redisTemplate.execute(redisScript, Collections.singletonList(key),
            limiter.replenishRate(), limiter.burstCapacity(), System.currentTimeMillis(), limiter.tokenCount(),
            getTimeStamp(limiter.period(), limiter.unit()));
        Long isAllowed = rateLimitResponse.get(0);
        Long remainTokens = rateLimitResponse.get(1);
        log.info("rate limit key [{}] result: isAllowed [{}] remainTokens [{}].", key, isAllowed, remainTokens);
        return isAllowed == 1;
    }
}
