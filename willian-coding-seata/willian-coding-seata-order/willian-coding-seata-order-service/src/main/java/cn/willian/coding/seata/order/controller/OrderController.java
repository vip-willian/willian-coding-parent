package cn.willian.coding.seata.order.controller;

import cn.willian.coding.seata.order.dto.OrderInfoDTO;
import cn.willian.coding.seata.order.param.OrderParam;
import cn.willian.coding.seata.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-10 12:21:29
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/get")
    public OrderInfoDTO getOrderInfo(@RequestParam(value = "orderId") Long orderId) {

        return orderService.getOrder(orderId);
    }

    @PostMapping("/save")
    public Long saveOrder(@RequestBody OrderParam param) {

        return orderService.saveOrder(param);
    }
}