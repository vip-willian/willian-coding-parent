package cn.willian.coding.redis.limiter.strategy;

import cn.willian.coding.redis.limiter.annotation.Limiter;
import cn.willian.coding.redis.limiter.enums.LimitWayEnum;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 11:50:39
 */
public interface RedisRateLimiter {

    /**
     * 指定限流方式
     * 
     * @return 限流方式
     */
    LimitWayEnum getLimitWay();

    /**
     * 请求接口是否放行
     * 
     * @param key 限流资源KEY
     * @param limiter 限流注解参数
     * @return 是否放行
     */
    boolean tryAcquire(String key, Limiter limiter);
}
