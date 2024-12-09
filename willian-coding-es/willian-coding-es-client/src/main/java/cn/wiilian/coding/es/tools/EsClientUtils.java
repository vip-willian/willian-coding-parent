package cn.wiilian.coding.es.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import cn.wiilian.coding.es.EsClientPool;
import cn.wiilian.coding.es.obj.PrimaryID;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 12:13:14
 */
@Slf4j
public class EsClientUtils {

    /**
     * 判断索引是否存在
     * 
     * @param indexName 索引名
     * @return 索引是否存在
     */
    public static boolean exists(String indexName) {

        ElasticsearchClient client = null;
        try {
            client = EsClientPool.getClient();
            ElasticsearchIndicesClient indexClient = client.indices();
            BooleanResponse booleanResponse = indexClient.exists(i -> i.index(indexName));
            return booleanResponse.value();
        } catch (IOException e) {
            log.error("连接ES失败", e);
        } finally {
            EsClientPool.returnClient(client);
        }
        return false;
    }

    /**
     * 更新索引
     *
     * @param indexName 索引名
     * @param indexJsonInputStream mapping映射文件
     * @return 是否更新成功
     */
    public static boolean updateIndex(String indexName, InputStream indexJsonInputStream) {

        // 默认强制更新
        return updateIndex(indexName, indexJsonInputStream, true);
    }

    /**
     * 更新索引
     * 
     * @param indexName 索引名
     * @param indexJsonInputStream mapping映射文件
     * @param isForce 是否强制更新
     * @return 是否更新成功
     */
    public static boolean updateIndex(String indexName, InputStream indexJsonInputStream, boolean isForce) {

        // 判断索引是否存在
        if (exists(indexName)) {
            // 如果存在，判断是否需要强制更新，如果需要强制更新，则删除原来的索引，重新新建索引
            if (isForce) {
                if (deleteIndex(indexName)) {
                    return createIndex(indexName, indexJsonInputStream);
                }
            } else {
                throw new RuntimeException("索引已经存在");
            }
        } else {
            // 索引不存在，直接创建
            return createIndex(indexName, indexJsonInputStream);
        }
        return false;
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名
     * @return 删除是否成功
     */
    private static boolean deleteIndex(String indexName) {

        ElasticsearchClient client = null;
        try {
            client = EsClientPool.getClient();
            ElasticsearchIndicesClient indexClient = client.indices();
            DeleteIndexResponse deleteIndexResponse = indexClient.delete(e -> e.index(indexName));
            return deleteIndexResponse.acknowledged();
        } catch (IOException e) {
            log.error("连接ES失败", e);
        } finally {
            EsClientPool.returnClient(client);
        }
        return false;
    }

    /**
     * 批量更新文档
     */
    public static <T extends PrimaryID> void batchUpdateDoc(String indexName, List<T> documents) {

        ElasticsearchClient client = null;
        try {
            client = EsClientPool.getClient();
            List<BulkOperation> bulkOperations = documents.stream()
                .map(doc -> BulkOperation
                    .of(b -> b.index(d -> d.index(indexName).id(String.valueOf(doc.getPrimaryId())).document(doc))))
                .collect(Collectors.toList());
            BulkResponse bulkResponse = client.bulk(r -> r.index(indexName).operations(bulkOperations));

            if (bulkResponse.errors()) {
                log.error("批量更新文档出现错误");
            } else {
                for (BulkResponseItem item : bulkResponse.items()) {
                    log.info("批量更新文档结果:{}:{}", item.id(), item.version());
                }
            }
        } catch (IOException e) {
            log.error("连接ES失败", e);
        } finally {
            EsClientPool.returnClient(client);
        }
    }

    /**
     * 批量删除文档
     */
    public static void batchDeleteDoc(String indexName, List<PrimaryID> deleteIds) {

        ElasticsearchClient client = null;
        try {
            client = EsClientPool.getClient();
            List<BulkOperation> bulkOperations = deleteIds.stream().map(id -> BulkOperation.of(
                // 指定操作的动作 ---> 删除操作
                b -> b.delete(d ->
                // 指定删除的索引
                d.index(indexName)
                    // 指定删除的文档ID
                    .id(id.getPrimaryId()))))
                .collect(Collectors.toList());
            BulkResponse bulkResponse = client.bulk(i -> i.index(indexName).operations(bulkOperations));
            for (BulkResponseItem item : bulkResponse.items()) {
                log.info("批量删除文档结果:{}:{}", item.id(), item.version());
            }
        } catch (IOException e) {
            log.error("连接ES失败", e);
        } finally {
            EsClientPool.returnClient(client);
        }
    }

    private static boolean createIndex(String indexName, InputStream indexJsonInputStream) {

        ElasticsearchClient client = null;
        try {
            client = EsClientPool.getClient();
            ElasticsearchIndicesClient indexClient = client.indices();
            CreateIndexResponse createIndexResponse = indexClient.create(e ->
            // 索引名称
            e.index(indexName)
                // 索引json文件
                .withJson(indexJsonInputStream));
            return createIndexResponse.acknowledged();
        } catch (IOException e) {
            log.error("连接ES失败", e);
        } finally {
            try {
                indexJsonInputStream.close();
            } catch (IOException e) {
                log.error("文件流获取失败", e);
            }
            EsClientPool.returnClient(client);
        }
        return false;
    }
}
