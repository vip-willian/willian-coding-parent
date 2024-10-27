package cn.willian.coding.redis.inter;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson2.JSON;

import cn.hutool.core.util.StrUtil;
import cn.willian.coding.redis.constants.RedisConstants;
import cn.willian.coding.redis.dto.UserDTO;
import cn.willian.coding.redis.utils.UserHolder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 09:10:46
 */
public class UserRefreshTokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public UserRefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            String userTokenKey = String.format(RedisConstants.LOGIN_TOKEN, token);
            String userJson = (String) stringRedisTemplate.opsForHash().get(userTokenKey, token);
            if (StrUtil.isNotBlank(userJson)) {
                UserDTO user = JSON.parseObject(userJson, UserDTO.class);
                UserHolder.setUser(user);
                // 重新刷新token过期时间
                stringRedisTemplate.expire(userTokenKey, 30, TimeUnit.MINUTES);
            }
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserHolder.clear();
    }
}
