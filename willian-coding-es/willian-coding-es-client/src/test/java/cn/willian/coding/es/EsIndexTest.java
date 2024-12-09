package cn.willian.coding.es;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.wiilian.coding.es.EsClientPool;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.IntegerNumberProperty;
import co.elastic.clients.elasticsearch._types.mapping.KeywordProperty;
import co.elastic.clients.elasticsearch._types.mapping.ObjectProperty;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.mapping.TextProperty;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteAliasResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import co.elastic.clients.elasticsearch.indices.GetAliasResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import co.elastic.clients.elasticsearch.indices.PutMappingResponse;
import co.elastic.clients.elasticsearch.indices.UpdateAliasesResponse;
import co.elastic.clients.elasticsearch.indices.get_alias.IndexAliases;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * ES索引相关操作
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-08 15:42:52
 */
@Slf4j
public class EsIndexTest {

    private ElasticsearchClient client;
    private ElasticsearchIndicesClient indexClient;

    @BeforeEach
    public void before() {
        client = EsClientPool.getClient();
        // 获得索引操作客户端
        indexClient = client.indices();
    }

    @AfterEach
    public void after() {
        if (client != null) {
            EsClientPool.returnClient(client);
        }
    }

    /**
     * 获取索引
     */
    @Test
    public void getIndexTest() throws Exception {

        GetIndexResponse getIndexResponse = indexClient.get(e -> e.index("willian_user_1"));
        Map<String, IndexState> result = getIndexResponse.result();
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    /**
     * 创建索引-基于属性方式
     */
    @Test
    public void createIndexWithPropertiesTest() throws Exception {

        BooleanResponse isExists = indexClient.exists(e -> e.index("willian_user_1"));
        if (!isExists.value()) {
            Map<String, Property> properties = this.getIndexProperties();
            CreateIndexResponse createIndexResponse = indexClient.create(e ->
            // 索引名称
            e.index("willian_user_1")
                // 索引设置
                .settings(s ->
                // 设置分片数量
                s.numberOfShards("3")
                    // 设置副本数量
                    .numberOfReplicas("3"))
                // 索引属性映射
                .mappings(m -> m.properties(properties))
                // 别名配置
                .aliases("willian_user", a -> a));
            boolean acknowledged = createIndexResponse.acknowledged();
            log.info("创建索引是否成功:{}", acknowledged);
        }
    }

    /**
     * 创建索引-基于JSON方式
     */
    @Test
    public void createIndexWithJsonTest() throws Exception {

        BooleanResponse isExists = indexClient.exists(e -> e.index("willian_user_2"));
        if (!isExists.value()) {
            try (InputStream indexJsonInputStream = getIndexJsonInputStream()) {
                CreateIndexResponse createIndexResponse = indexClient.create(e ->
                // 索引名称
                e.index("willian_user_2")
                    // 索引json文件
                    .withJson(indexJsonInputStream));
                boolean acknowledged = createIndexResponse.acknowledged();
                log.info("创建索引是否成功:{}", acknowledged);
            }
        }
    }

    /**
     * 更新索引-增加属性字段
     */
    @Test
    public void updateIndexTest() throws Exception {

        PutMappingResponse putMappingResponse = indexClient.putMapping(e -> e.index("willian_user_2")
            .properties("workUnit", Property.of(p -> p.text(TextProperty.of(t -> t.index(false))))));
        boolean acknowledged = putMappingResponse.acknowledged();
        log.info("更新索引是否成功:{}", acknowledged);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndexTest() throws Exception {

        DeleteIndexResponse deleteIndexResponse = indexClient.delete(e -> e.index("willian_user_2"));
        boolean acknowledged = deleteIndexResponse.acknowledged();
        log.info("删除索引是否成功:{}", acknowledged);
    }

    /**
     * 添加别名
     */
    @Test
    public void addIndexAliasTest() throws Exception {

        UpdateAliasesResponse updateAliasesResponse = indexClient.updateAliases(
            update -> update.actions(action -> action.add(e -> e.index("willian_user202109").alias("willian_user"))));
        boolean acknowledged = updateAliasesResponse.acknowledged();
        log.info("更新索引别名是否成功:{}", acknowledged);
    }

    /**
     * 删除别名
     */
    @Test
    public void deleteIndexAliasTest() throws Exception {

        DeleteAliasResponse deleteAliasResponse =
            indexClient.deleteAlias(delete -> delete.index("willian_user202109").name("willian_user"));
        boolean acknowledged = deleteAliasResponse.acknowledged();
        log.info("删除索引别名是否成功:{}", acknowledged);
    }

    /**
     * 获取索引别名
     */
    @Test
    public void getIndexAliasTest() throws Exception {

        GetAliasResponse getAliasResponse = indexClient.getAlias(e -> e.name("willian_user"));
        Map<String, IndexAliases> result = getAliasResponse.result();
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    private Map<String, Property> getIndexProperties() {

        Map<String, Property> properties = new HashMap<>();
        properties.put("name", Property.of(p -> p.text(TextProperty.of(t -> t.index(true).analyzer("ik_max_word")))));
        properties.put("age", Property.of(p -> p.integer(IntegerNumberProperty.of(t -> t.index(false)))));
        properties.put("idCard", Property.of(p -> p.keyword(KeywordProperty.of(t -> t.index(true)))));
        properties.put("phone", Property.of(p -> p.keyword(KeywordProperty.of(t -> t.index(true)))));
        // 地址字段属性
        Map<String, Property> addressProperties = new HashMap<>();
        addressProperties.put("province", Property.of(p -> p.keyword(KeywordProperty.of(t -> t.index(true)))));
        addressProperties.put("city", Property.of(p -> p.keyword(KeywordProperty.of(t -> t.index(true)))));
        addressProperties.put("region", Property.of(p -> p.keyword(KeywordProperty.of(t -> t.index(true)))));
        addressProperties.put("detail",
            Property.of(p -> p.text(TextProperty.of(t -> t.index(true).analyzer("ik_max_word")))));

        properties.put("address", Property.of(p -> p.object(ObjectProperty.of(t -> t.properties(addressProperties)))));

        return properties;
    }

    private InputStream getIndexJsonInputStream() {

        return EsIndexTest.class.getClassLoader().getResourceAsStream("willian_user_mapping.json");
    }
}
