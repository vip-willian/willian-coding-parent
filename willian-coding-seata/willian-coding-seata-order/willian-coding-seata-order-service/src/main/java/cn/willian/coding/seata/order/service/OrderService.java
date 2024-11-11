package cn.willian.coding.seata.order.service;

import cn.willian.coding.seata.order.domain.Order;
import cn.willian.coding.seata.order.dto.OrderInfoDTO;
import cn.willian.coding.seata.order.param.OrderParam;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:37:15
 */
public interface OrderService {

    /**
     * 创建订单
     */
    Long saveOrder(OrderParam param);

    /**
     * 获取订单
     */
    OrderInfoDTO getOrder(Long orderId);
}
