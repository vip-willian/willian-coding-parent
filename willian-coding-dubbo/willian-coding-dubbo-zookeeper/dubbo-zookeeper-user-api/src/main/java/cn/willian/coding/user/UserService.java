package cn.willian.coding.user;

import cn.willian.coding.user.dto.UserDTO;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-27 23:34:24
 */
public interface UserService {

    /**
     * 获取用户信息
     * 
     * @param userId 用户ID
     * 
     * @return 用户信息
     */
    UserDTO getUser(Long userId);
}
