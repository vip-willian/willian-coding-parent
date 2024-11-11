package cn.willian.coding.seata.order.remote.dubbo;

import cn.willian.coding.seata.goods.GoodsServiceFacade;
import cn.willian.coding.seata.goods.dto.GoodsInventoryDeductDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 13:59:57
 */
@Service
public class GoodsDubboRemoteService {


    @DubboReference
    private GoodsServiceFacade goodsServiceFacade;

    /**
     * 扣减商品库存
     */
    public void deductInventory(List<GoodsInventoryDeductDTO> deductGoods) {

        goodsServiceFacade.deductInventory(deductGoods);
    }
}
