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
 * 滑动窗口限流
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 11:59:57
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class SlideWindowRedisRateLimiter extends AbstractRedisRateLimiter {

    protected DefaultRedisScript<List> redisScript;

    @Override
    public void afterPropertiesSet() throws Exception {
        redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis-slide-rate-limiter.lua")));
        redisScript.setResultType(List.class);
    }

    @Override
    public LimitWayEnum getLimitWay() {
        return LimitWayEnum.SLIDE_WINDOW;
    }

    @Override
    public boolean tryAcquire(String key, Limiter limiter) {

        String threadId = String.valueOf(Thread.currentThread().getId());
        List<Long> result = redisTemplate.execute(redisScript, Collections.singletonList(key), threadId,
            getTimeStamp(limiter.period(), limiter.unit()), limiter.limitCount(), System.currentTimeMillis());
        log.info("剩余请求数：{}", result.get(1));
        return result.get(0) == 1;
    }
}
