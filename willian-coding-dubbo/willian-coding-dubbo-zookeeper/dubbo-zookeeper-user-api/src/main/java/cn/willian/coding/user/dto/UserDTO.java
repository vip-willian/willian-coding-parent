package cn.willian.coding.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-27 23:35:04
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
}
