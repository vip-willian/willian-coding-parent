package cn.willian.coding.seata.goods.facade;

import cn.willian.coding.seata.goods.GoodsServiceFacade;
import cn.willian.coding.seata.goods.dto.GoodsInventoryDeductDTO;
import cn.willian.coding.seata.goods.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 14:12:41
 */
@DubboService
public class GoodsServiceFacadeImpl implements GoodsServiceFacade {

    @Resource
    private GoodsService goodsService;

    @Override
    public void deductInventory(List<GoodsInventoryDeductDTO> deductGoods) {

        goodsService.deductInventory(deductGoods);
    }
}
