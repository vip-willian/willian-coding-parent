package cn.willian.coding.redis.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import cn.willian.coding.redis.dto.UserDTO;
import cn.willian.coding.redis.utils.UserHolder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 09:10:46
 */
public class UserLoginInterfaceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        UserDTO user = UserHolder.getUser();
        if (user != null) {
            return true;
        }
        return false;
    }
}
