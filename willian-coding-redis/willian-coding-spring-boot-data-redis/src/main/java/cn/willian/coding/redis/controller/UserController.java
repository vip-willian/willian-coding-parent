package cn.willian.coding.redis.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.willian.coding.redis.dto.UserDTO;
import cn.willian.coding.redis.param.LoginUserParam;
import cn.willian.coding.redis.service.UserService;
import cn.willian.coding.redis.utils.Response;
import cn.willian.coding.redis.utils.UserHolder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 07:37:44
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据手机号获取验证码
     * 
     * @param phone 手机号
     */
    @GetMapping(value = "/login/verify/code")
    public Response<Void> sendVerifyCode(@RequestParam("phone") String phone) {

        return userService.sendVerifyCode(phone);
    }

    /**
     * 登录接口
     *
     * @param userParam 参数信息
     * @return 登录用户TOKEN
     */
    @PostMapping(value = "/login")
    public Response<String> login(@RequestBody LoginUserParam userParam) {

        return userService.login(userParam.getPhone(), userParam.getPassword(), userParam.getVerifyCode());
    }

    /**
     * 获取我的登录信息
     *
     * @return 我的登录信息
     */
    @GetMapping(value = "/mine")
    public Response<UserDTO> mine() {

        UserDTO user = UserHolder.getUser();
        return Response.ok(user);
    }
}
