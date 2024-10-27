package cn.willian.coding.redis.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.willian.coding.redis.inter.UserLoginInterfaceInterceptor;
import cn.willian.coding.redis.inter.UserRefreshTokenInterceptor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-26 23:16:00
 */
@Configuration
@EnableWebMvc
public class RedisConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new UserLoginInterfaceInterceptor()).excludePathPatterns("/user/login/verify/code",
                "/user/login");
        registry.addInterceptor(new UserRefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**");
    }

    // @Bean
    // public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    // // 创建RedisTemplate对象
    // RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    // // 设置连接工厂
    // redisTemplate.setConnectionFactory(redisConnectionFactory);
    // // 设置key序列化
    // redisTemplate.setKeySerializer(RedisSerializer.string());
    // redisTemplate.setHashKeySerializer(RedisSerializer.string());
    // // 设置value序列化
    // GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    // redisTemplate.setValueSerializer(jsonRedisSerializer);
    // redisTemplate.setHashValueSerializer(jsonRedisSerializer);
    // return redisTemplate;
    // }

}
