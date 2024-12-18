package cn.willian.coding.designmode.behavior.iterator.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 20:54:45
 */
@Data
@AllArgsConstructor(staticName = "of")
public class Order {

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单金额
     */
    private Double orderAmount;
}
