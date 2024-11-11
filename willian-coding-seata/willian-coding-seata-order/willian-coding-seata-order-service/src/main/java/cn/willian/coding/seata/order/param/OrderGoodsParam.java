package cn.willian.coding.seata.order.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:38:57
 */
@Data
public class OrderGoodsParam {

    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 购买单价
     */
    private BigDecimal unitPrice;
    /**
     * 购买数量
     */
    private Integer quantity;
}
