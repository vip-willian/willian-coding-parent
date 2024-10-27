package cn.willian.coding.redis.param;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 08:27:29
 */
@Data
public class LoginUserParam {

    /**
     * 手机号
     */
    private String phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String verifyCode;
}
