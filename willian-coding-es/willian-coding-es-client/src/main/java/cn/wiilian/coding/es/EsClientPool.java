package cn.wiilian.coding.es;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用对象池，复用资源
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 09:52:10
 */
@Slf4j
public class EsClientPool {

    private static final GenericObjectPoolConfig<ElasticsearchClient> poolConfig = new GenericObjectPoolConfig<>();
    // 利用对象工厂类和配置类生成对象池
    private static final GenericObjectPool<ElasticsearchClient> objectPool =
        new GenericObjectPool<>(new EsClientPoolFactory(), poolConfig);

    // 采用默认配置maxTotal是8，池中有8个client
    static {
        poolConfig.setMaxIdle(200);
        poolConfig.setMaxTotal(20);
        poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(3));
    }

    public static ElasticsearchClient getClient() {

        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            log.error("get es client failed", e);
            throw new RuntimeException(e);
        }
    }

    public static void returnClient(ElasticsearchClient client) {

        if (client == null) {
            return;
        }
        try {
            objectPool.returnObject(client);
        } catch (Exception e) {
            log.error("return es client failed", e);
            throw new RuntimeException(e);
        }
    }
}
