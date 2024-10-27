package cn.willian.coding.redis.constants;

import lombok.experimental.UtilityClass;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 08:40:24
 */
@UtilityClass
public class RedisConstants {

    public static final String LOGIN_PHONE_VERIFY_CODE = "login:phone:verify:code:%s";
    public static final String LOGIN_TOKEN = "user:login:token:%s";
}
