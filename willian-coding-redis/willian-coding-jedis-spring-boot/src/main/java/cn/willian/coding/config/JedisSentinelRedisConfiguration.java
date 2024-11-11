package cn.willian.coding.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 哨兵配置
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-07 14:13:57
 */
@Configuration
@ConditionalOnProperty(value = "spring.redis.mode", havingValue = "sentinel")
public class JedisSentinelRedisConfiguration extends AbstractJedisRedisConfiguration {

    @Bean
    @ConditionalOnBean({RedisProperties.class, JedisPoolConfig.class})
    public RedisSentinelConfiguration sentinelConfiguration(RedisProperties redisProperties) {

        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        // 哨兵master
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        sentinelConfiguration.master(sentinel.getMaster());
        // 哨兵节点
        Set<RedisNode> redisNodeSet = new HashSet<>();
        List<String> sentinelNodes = sentinel.getNodes();
        for (String ipPort : sentinelNodes) {
            String[] ipAndPort = ipPort.split(":");
            redisNodeSet.add(new RedisNode(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1])));
        }
        sentinelConfiguration.setSentinels(redisNodeSet);
        return sentinelConfiguration;
    }

    /**
     * 哨兵连接
     */
    @Bean
    @ConditionalOnBean({RedisSentinelConfiguration.class, JedisPoolConfig.class})
    public JedisConnectionFactory jedisConnectionFactory(RedisSentinelConfiguration sentinelConfiguration, JedisPoolConfig jedisPoolConfig) {

        return new JedisConnectionFactory(sentinelConfiguration, jedisPoolConfig);
    }
}
