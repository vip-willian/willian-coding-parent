package cn.willian.coding.seata.order.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.willian.coding.seata.goods.dto.GoodsInventoryDeductDTO;
import cn.willian.coding.seata.order.dao.OrderDetailMapper;
import cn.willian.coding.seata.order.dao.OrderMapper;
import cn.willian.coding.seata.order.domain.Order;
import cn.willian.coding.seata.order.domain.OrderDetail;
import cn.willian.coding.seata.order.dto.OrderInfoDTO;
import cn.willian.coding.seata.order.enums.OrderStatusEnum;
import cn.willian.coding.seata.order.param.OrderGoodsParam;
import cn.willian.coding.seata.order.param.OrderParam;
import cn.willian.coding.seata.order.remote.dubbo.GoodsDubboRemoteService;
import cn.willian.coding.seata.order.remote.dubbo.UserDubboRemoteService;
import cn.willian.coding.seata.order.service.OrderService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:42:25
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private UserDubboRemoteService userDubboRemoteService;
    @Resource
    private GoodsDubboRemoteService goodsDubboRemoteService;

    @Override
    public OrderInfoDTO getOrder(Long orderId) {
        Optional<Order> order = orderMapper.selectByPrimaryKey(orderId);
        return order.map(v -> {
            OrderInfoDTO orderInfo = new OrderInfoDTO();
            orderInfo.setOrder(v);
            orderInfo.setDetails(orderDetailMapper.wrapper().eq(OrderDetail::getOrderId, orderId).list());
            return orderInfo;
        }).orElseThrow(() -> new RuntimeException("订单不存在"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveOrder(OrderParam param) {

        Long orderId = IdUtil.getSnowflakeNextId();
        List<OrderDetail> orderDetails = this.getOrderDetails(orderId, param);
        Order order = this.getOrder(param, orderDetails, orderId);

        // 创建订单
        orderMapper.insertSelective(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailMapper.insertSelective(orderDetail);
        }

        // 扣减商品库存
        List<GoodsInventoryDeductDTO> deductGoods = this.getDeductGoods(orderDetails);
        goodsDubboRemoteService.deductInventory(deductGoods);

        // 扣减用户账户金额
        userDubboRemoteService.deductUserAmount(param.getUserId(), order.getTotalPrice());

        // 模拟订单报错
        // throw new RuntimeException("订单报错");
        return orderId;
    }

    private List<GoodsInventoryDeductDTO> getDeductGoods(List<OrderDetail> orderDetails) {

        return orderDetails.stream().map(v -> {
            GoodsInventoryDeductDTO deductGoods = new GoodsInventoryDeductDTO();
            deductGoods.setGoodsId(v.getGoodsId());
            deductGoods.setQuantity(v.getQuantity());
            return deductGoods;
        }).collect(Collectors.toList());
    }

    private Order getOrder(OrderParam param, List<OrderDetail> orderDetails, Long orderId) {

        Order order = new Order();
        order.setId(orderId);
        order.setUserId(param.getUserId());
        order.setTotalPrice(this.getTotalPrice(orderDetails));
        order.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        return order;
    }

    private BigDecimal getTotalPrice(List<OrderDetail> orderDetails) {
        // 获取订单总价
        return orderDetails.stream().map(v -> new BigDecimal(v.getQuantity()).multiply(v.getUnitPrice())).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    private List<OrderDetail> getOrderDetails(Long orderId, OrderParam param) {

        List<OrderDetail> orderDetails = Lists.newArrayListWithCapacity(param.getOrderGoods().size());
        for (OrderGoodsParam orderGood : param.getOrderGoods()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(IdUtil.getSnowflakeNextId());
            orderDetail.setUserId(param.getUserId());
            orderDetail.setOrderId(orderId);
            orderDetail.setGoodsId(orderGood.getGoodsId());
            orderDetail.setUnitPrice(orderGood.getUnitPrice());
            orderDetail.setQuantity(orderGood.getQuantity());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
