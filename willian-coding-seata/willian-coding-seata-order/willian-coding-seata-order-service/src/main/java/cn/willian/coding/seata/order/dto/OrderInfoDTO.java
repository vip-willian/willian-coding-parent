package cn.willian.coding.seata.order.dto;

import cn.willian.coding.seata.order.domain.Order;
import cn.willian.coding.seata.order.domain.OrderDetail;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 12:11:25
 */
@Data
public class OrderInfoDTO {

    private Order order;
    private List<OrderDetail> details;
}
