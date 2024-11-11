package cn.willian.coding.seata.order.param;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:38:57
 */
@Data
public class OrderParam {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单商品参数
     */
    private List<OrderGoodsParam> orderGoods;
}
