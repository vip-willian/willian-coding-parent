package cn.willian.coding.redis.limiter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import cn.willian.coding.redis.limiter.enums.LimitTypeEnum;
import cn.willian.coding.redis.limiter.enums.LimitWayEnum;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 22:22:05
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limiter {

    /**
     * key
     */
    String key() default "";

    /**
     * 指定时间范围内最大请求数量
     */
    int limitCount() default 200;

    /**
     * 限流方式
     */
    LimitWayEnum limitWay() default LimitWayEnum.FIXED_WINDOW;

    /**
     * 限流类型
     */
    LimitTypeEnum limitType() default LimitTypeEnum.CUSTOMER;

    /**
     * 令牌桶每秒填充平均速率
     *
     * @return replenishRate
     */
    int replenishRate() default 1;

    /**
     * 令牌桶总容量
     *
     * @return burstCapacity
     */
    int burstCapacity() default 3;

    /**
     * 请求需要的token数量
     *
     * @return burstCapacity
     */
    int tokenCount() default 1;

    /**
     * 时间范围
     */
    long period();

    /**
     * 限流时间维度，默认为秒 支持秒，分钟，小时，天
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
