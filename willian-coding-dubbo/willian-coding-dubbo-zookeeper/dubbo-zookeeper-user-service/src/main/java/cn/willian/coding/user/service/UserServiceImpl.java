package cn.willian.coding.user.service;

import org.apache.dubbo.config.annotation.DubboService;

import cn.willian.coding.user.UserService;
import cn.willian.coding.user.dto.UserDTO;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 00:05:34
 */
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getUser(Long userId) {

        UserDTO user = new UserDTO();
        user.setUserId(userId);
        user.setUserName("林冲");
        return user;
    }
}
