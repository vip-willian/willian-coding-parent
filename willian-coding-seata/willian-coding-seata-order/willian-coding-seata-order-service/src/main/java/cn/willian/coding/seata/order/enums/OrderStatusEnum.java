package cn.willian.coding.seata.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 11:24:44
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    WAIT_PAY(1, "待支付"),
    PAY_FINISH(2, "已支付"),
    ORDER_FINISH(3, "已完成"),

    ;

    private final Integer code;
    private final String name;
}
