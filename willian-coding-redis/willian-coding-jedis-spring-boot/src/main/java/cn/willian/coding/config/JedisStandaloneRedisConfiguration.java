package cn.willian.coding.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单机配置
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-07 14:13:57
 */
@Configuration
@ConditionalOnProperty(value = "spring.redis.mode", havingValue = "standalone", matchIfMissing = true)
public class JedisStandaloneRedisConfiguration extends AbstractJedisRedisConfiguration {

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(RedisProperties properties) {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        config.setDatabase(properties.getDatabase());
        config.setPassword(RedisPassword.of(properties.getPassword()));
        return config;
    }

    @Bean
    @SuppressWarnings("all")
    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig,
                                                         RedisStandaloneConfiguration redisStandaloneConfiguration) {

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        connectionFactory.setPoolConfig(jedisPoolConfig);
        return connectionFactory;
    }
}
