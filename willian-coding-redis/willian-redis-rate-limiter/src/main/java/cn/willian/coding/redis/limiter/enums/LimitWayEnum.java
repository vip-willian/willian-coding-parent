package cn.willian.coding.redis.limiter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 限流方式
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 22:20:16
 */
@Getter
@AllArgsConstructor
public enum LimitWayEnum {

    /**
     * 固定时间窗口限流
     */
    FIXED_WINDOW("fixedWindowRateLimiterLuaScript"),

    /**
     * 滑动时间窗口限流
     */
    SLIDE_WINDOW("slideWindowRateLimiterLuaScript"),

    /**
     * 令牌桶
     */
    TOKEN_BUCKET("tokenBucketRateLimiterLuaScript"),

    ;

    private final String code;
}
