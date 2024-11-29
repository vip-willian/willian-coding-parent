package cn.willian.coding.redis.limiter.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.willian.coding.redis.limiter.enums.LimitWayEnum;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 11:54:19
 */
public class RedisRateLimiterFactory {

    private static final Map<LimitWayEnum, RedisRateLimiter> redisRateLimiterMap = new ConcurrentHashMap<>();

    public static RedisRateLimiter getRedisRateLimiter(LimitWayEnum limitWay) {
        return redisRateLimiterMap.getOrDefault(limitWay, null);
    }

    public static void register(LimitWayEnum limitWay, RedisRateLimiter redisRateLimiter) {
        redisRateLimiterMap.put(limitWay, redisRateLimiter);
    }
}
