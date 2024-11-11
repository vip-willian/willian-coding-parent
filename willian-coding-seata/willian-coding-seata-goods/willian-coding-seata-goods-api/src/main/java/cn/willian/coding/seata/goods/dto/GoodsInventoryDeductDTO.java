package cn.willian.coding.seata.goods.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 13:12:34
 */
@Data
public class GoodsInventoryDeductDTO implements Serializable {

    /**
     * 扣减商品ID
     */
    private Long goodsId;
    /**
     * 扣减商品数量
     */
    private Integer quantity;
}
