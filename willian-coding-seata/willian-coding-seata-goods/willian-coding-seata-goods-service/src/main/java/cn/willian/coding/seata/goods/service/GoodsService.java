package cn.willian.coding.seata.goods.service;

import cn.willian.coding.seata.goods.dto.GoodsInventoryDeductDTO;

import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:37:15
 */
public interface GoodsService {

    /**
     * 扣减商品库存
     */
    void deductInventory(List<GoodsInventoryDeductDTO> deductGoods);
}
