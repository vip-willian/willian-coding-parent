package cn.willian.coding.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * 集群配置
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-07 14:13:57
 */
@Configuration
@ConditionalOnProperty(value = "spring.redis.mode", havingValue = "cluster")
public class JedisClusterRedisConfiguration extends AbstractJedisRedisConfiguration {

    /**
     * 集群配置
     */
    @Bean
    @ConditionalOnBean({RedisProperties.class})
    public RedisClusterConfiguration redisClusterConfiguration(RedisProperties redisProperties) {

        RedisProperties.Cluster cluster = redisProperties.getCluster();
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        Set<RedisNode> nodes = new HashSet<>();
        for (String ipPort : cluster.getNodes()) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1])));
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(cluster.getMaxRedirects());
        return redisClusterConfiguration;
    }

    /**
     * 集群连接
     */
    @Bean
    @ConditionalOnBean({RedisClusterConfiguration.class, JedisPoolConfig.class})
    public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration clusterConfiguration, JedisPoolConfig jedisPoolConfig) {

        return new JedisConnectionFactory(clusterConfiguration, jedisPoolConfig);
    }
}
