package cn.willian.coding.seata.goods;

import cn.willian.coding.seata.goods.dto.GoodsInventoryDeductDTO;

import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 14:11:35
 */
public interface GoodsServiceFacade {

    /**
     * 扣减商品库存
     */
    void deductInventory(List<GoodsInventoryDeductDTO> deductGoods);
}
