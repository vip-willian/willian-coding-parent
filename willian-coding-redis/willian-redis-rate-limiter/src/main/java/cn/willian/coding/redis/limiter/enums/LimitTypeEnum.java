package cn.willian.coding.redis.limiter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 22:20:16
 */
@Getter
@AllArgsConstructor
public enum LimitTypeEnum {

    /**
     * 自定义限流
     */
    CUSTOMER,

    /**
     * IP限流
     */
    IP
}
