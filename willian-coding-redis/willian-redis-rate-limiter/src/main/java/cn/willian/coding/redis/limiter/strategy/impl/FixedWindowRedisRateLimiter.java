package cn.willian.coding.redis.limiter.strategy.impl;

import java.util.Collections;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import cn.willian.coding.redis.limiter.annotation.Limiter;
import cn.willian.coding.redis.limiter.enums.LimitWayEnum;
import cn.willian.coding.redis.limiter.strategy.AbstractRedisRateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * 固定窗口限流
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 11:59:40
 */
@Slf4j
@Service
public class FixedWindowRedisRateLimiter extends AbstractRedisRateLimiter {

    protected DefaultRedisScript<Long> redisScript;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis-fixed-rate-limiter.lua")));
        redisScript.setResultType(Long.class);
    }

    @Override
    public LimitWayEnum getLimitWay() {
        return LimitWayEnum.FIXED_WINDOW;
    }

    @Override
    public boolean tryAcquire(String key, Limiter limiter) {

        Long requestNumber = redisTemplate.execute(redisScript, Collections.singletonList(key), limiter.limitCount(),
            getTimeStamp(limiter.period(), limiter.unit()));
        if (requestNumber == -1) {
            return false;
        }
        log.info("剩余可用请求数量：{} 次", requestNumber);
        return true;
    }
}
