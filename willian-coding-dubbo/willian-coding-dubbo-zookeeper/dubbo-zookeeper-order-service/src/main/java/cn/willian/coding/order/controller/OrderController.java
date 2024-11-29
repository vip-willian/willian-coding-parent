package cn.willian.coding.order.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.willian.coding.user.UserService;
import cn.willian.coding.user.dto.UserDTO;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 00:07:58
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @DubboReference
    private UserService userService;

    @GetMapping(value = "/user")
    public UserDTO getOrderUser(@RequestParam(value = "userId") Long userId) {

        return userService.getUser(userId);
    }
}
