package cn.willian.coding.seata.goods.service.impl;

import cn.willian.coding.seata.goods.dao.GoodsMapper;
import cn.willian.coding.seata.goods.domain.Goods;
import cn.willian.coding.seata.goods.dto.GoodsInventoryDeductDTO;
import cn.willian.coding.seata.goods.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:42:25
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deductInventory(List<GoodsInventoryDeductDTO> deductGoods) {

        List<Goods> goodsLists = goodsMapper.selectByIds(deductGoods.stream().map(GoodsInventoryDeductDTO::getGoodsId).collect(Collectors.toList()));
        Map<Long, Goods> goodsMap = goodsLists.stream().collect(Collectors.toMap(Goods::getId, Function.identity()));
        for (GoodsInventoryDeductDTO deductGood : deductGoods) {
            Goods goods = goodsMap.get(deductGood.getGoodsId());
            if (goods == null) {
                throw new RuntimeException("商品不存在");
            }
            if (goods.getGoodsInventory() < deductGood.getQuantity()) {
                throw new RuntimeException("商品库存不足");
            }
            goods.setGoodsInventory(goods.getGoodsInventory() - deductGood.getQuantity());
            goodsMapper.updateByPrimaryKey(goods);
        }
    }
}
