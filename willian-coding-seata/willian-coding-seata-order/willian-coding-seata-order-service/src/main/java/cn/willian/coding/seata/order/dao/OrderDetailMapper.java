package cn.willian.coding.seata.order.dao;

import cn.willian.coding.seata.order.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:05
 */
@Mapper
public interface OrderDetailMapper extends io.mybatis.mapper.Mapper<OrderDetail, Long> {

}
