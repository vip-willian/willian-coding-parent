package cn.willian.coding.redis.utils;

import cn.willian.coding.redis.dto.UserDTO;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 09:16:27
 */
public class UserHolder {

    private static ThreadLocal<UserDTO> userHolder = new ThreadLocal<>();

    public static UserDTO getUser() {
        return userHolder.get();
    }

    public static void setUser(UserDTO user) {
        userHolder.set(user);
    }

    public static void clear() {
        userHolder.remove();
    }
}
