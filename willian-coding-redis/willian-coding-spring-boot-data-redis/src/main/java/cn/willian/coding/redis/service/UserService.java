package cn.willian.coding.redis.service;

import cn.willian.coding.redis.utils.Response;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 07:37:55
 */
public interface UserService {

    /**
     * 根据手机号获取验证码
     *
     * @param phone 手机号
     */
    Response<Void> sendVerifyCode(String phone);

    /**
     * 登录接口
     *
     * @param phone 手机号
     * @param password 密码
     * @param verifyCode 验证码
     * @return 登录用户TOKEN
     */
    Response<String> login(String phone, String password, String verifyCode);
}
