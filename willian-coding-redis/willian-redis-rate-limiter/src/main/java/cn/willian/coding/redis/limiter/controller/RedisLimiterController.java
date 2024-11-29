package cn.willian.coding.redis.limiter.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.willian.coding.redis.limiter.annotation.Limiter;
import cn.willian.coding.redis.limiter.enums.LimitWayEnum;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 23:10:32
 */
@RestController
public class RedisLimiterController {

    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();

    @Limiter(key = "testFixedRateLimiter", period = 10, limitCount = 3)
    @GetMapping("/testFixedRateLimiter")
    public int testFixedRateLimiter() {

        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    @Limiter(key = "testSlideRateLimiter", period = 5, limitCount = 5, limitWay = LimitWayEnum.SLIDE_WINDOW)
    @GetMapping("/testSlideRateLimiter")
    public int testSlideRateLimiter() {

        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    @Limiter(key = "testTokenBucketRateLimiter", period = 1, burstCapacity = 10, replenishRate = 1,
        limitWay = LimitWayEnum.TOKEN_BUCKET)
    @GetMapping("/testTokenBucketRateLimiter")
    public int testTokenBucketRateLimiter() {

        return ATOMIC_INTEGER_3.incrementAndGet();
    }
}
