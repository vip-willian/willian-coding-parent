package cn.willian.coding.redis.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.willian.coding.redis.constants.RedisConstants;
import cn.willian.coding.redis.converter.DataConverter;
import cn.willian.coding.redis.dao.UserDao;
import cn.willian.coding.redis.domain.User;
import cn.willian.coding.redis.dto.UserDTO;
import cn.willian.coding.redis.service.UserService;
import cn.willian.coding.redis.utils.Response;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-27 07:37:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Response<Void> sendVerifyCode(String phone) {

        // 校验手机号
        if (!Validator.isMobile(phone)) {
            return Response.fail("手机号不合法,请输入正确的手机号");
        }
        // 模拟生成验证码并发送
        String verifyCode = RandomUtil.randomNumbers(6);
        System.out.println("当前手机号 phone = " + phone + " verifyCode = " + verifyCode);
        // 放入redis缓存中,设置5分钟过期时间
        stringRedisTemplate.opsForValue().set(String.format(RedisConstants.LOGIN_PHONE_VERIFY_CODE, phone), verifyCode,
                5, TimeUnit.MINUTES);
        return Response.ok();
    }

    @Override
    public Response<String> login(String phone, String password, String verifyCode) {

        // 校验手机号
        if (!Validator.isMobile(phone)) {
            return Response.fail("手机号不合法,请输入正确的手机号");
        }
        // 校验手机号和验证码是否匹配
        // 手机号、验证码登录
        if (StrUtil.isNotBlank(verifyCode)) {
            String key = String.format(RedisConstants.LOGIN_PHONE_VERIFY_CODE, phone);
            String cacheVerifyCode = stringRedisTemplate.opsForValue().get(key);
            if (!verifyCode.equals(cacheVerifyCode)) {
                return Response.fail("验证码不正确，请重新输入");
            }
        }
        // 将登录用户信息放入到全局redis作为session共享
        String token = RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz", 9);
        UserDTO user = getUser(phone);

        String userTokenKey = String.format(RedisConstants.LOGIN_TOKEN, token);
        stringRedisTemplate.opsForHash().put(userTokenKey, token, JSON.toJSONString(user));
        stringRedisTemplate.expire(userTokenKey, 30, TimeUnit.MINUTES);
        return Response.ok(token);
    }

    private UserDTO getUser(String phone) {

        // 判断用户信息是否存在
        User user = userDao.getByPhone(phone);
        if (user == null) {
            user = createUser(phone);
            userDao.insert(user);
        }
        return DataConverter.MAPPER.convert(user);
    }

    private User createUser(String phone) {

        User user = new User();
        user.setUserName("user_" + RandomUtil.randomNumbers(6));
        user.setPhone(phone);
        user.setPassword(RandomUtil.randomNumbers(10));
        return user;
    }
}
