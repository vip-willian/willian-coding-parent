package cn.willian.coding.seata.goods.dao;

import cn.willian.coding.seata.goods.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:05
 */
@Mapper
public interface GoodsMapper extends io.mybatis.mapper.Mapper<Goods, Long> {

    List<Goods> selectByIds(@Param("ids") List<Long> ids);
}
