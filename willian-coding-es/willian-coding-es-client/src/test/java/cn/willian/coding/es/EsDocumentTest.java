package cn.willian.coding.es;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import cn.wiilian.coding.es.EsClientPool;
import cn.wiilian.coding.es.obj.EsAddress;
import cn.wiilian.coding.es.obj.EsUser;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import lombok.extern.slf4j.Slf4j;

/**
 * ES文档相关操作
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-08 15:42:52
 */
@Slf4j
public class EsDocumentTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private ElasticsearchClient client;

    @BeforeEach
    public void before() {
        client = EsClientPool.getClient();
    }

    @AfterEach
    public void after() {
        if (client != null) {
            EsClientPool.returnClient(client);
        }
    }

    /**
     * 查看文档
     */
    @Test
    public void getDocumentTest() throws Exception {

        GetResponse<EsUser> getResponse = client.get(v -> v.index("willian_user").id("1"), EsUser.class);
        if (getResponse.found()) {
            EsUser esUser = getResponse.source();
            log.info("获取文档结果:{}", objectMapper.writeValueAsString(esUser));
        } else {
            log.info("未查询到文档");
        }
    }

    /**
     * 插入文档
     */
    @Test
    public void insertDocumentTest() throws Exception {

        EsAddress address = new EsAddress();
        address.setProvince("浙江省");
        address.setCity("杭州市");
        address.setRegion("西湖区");
        address.setDetail("东洲街道120号");

        EsUser user = new EsUser();
        user.setName("宋江");
        user.setAge(20);
        user.setIdCard("33345344563");
        user.setPhone("15922359234");
        user.setAddress(address);

        IndexResponse indexResponse = client.index(i -> i.index("willian_user").id("1").document(user));
        log.info("添加文档结果:{}", indexResponse.version());
    }

    /**
     * 批量插入文档
     */
    @Test
    public void batchInsertDocumentTest() throws Exception {

        List<EsUser> users = this.getMockUsers();
        // 默认操作是添加、更新
        List<BulkOperation> bulkOperations = users.stream()
            .map(user -> BulkOperation.of(v -> v.index(d -> d.id(String.valueOf(user.getId())).document(user))))
            .collect(Collectors.toList());
        BulkResponse bulkResponse = client.bulk(i -> i.index("willian_user").operations(bulkOperations));
        for (BulkResponseItem item : bulkResponse.items()) {
            log.info("批量添加文档结果:{}:{}", item.id(), item.version());
        }
    }

    /**
     * 更新文档
     */
    @Test
    public void updateDocumentWithAllTest() throws Exception {

        EsAddress address = new EsAddress();
        address.setProvince("浙江省");
        address.setCity("杭州市");
        address.setRegion("西湖区");
        address.setDetail("东洲街道120号");

        EsUser user = new EsUser();
        user.setName("宋江");
        user.setAge(48);
        user.setIdCard("339900194802193204");
        user.setPhone("15922359234");
        user.setAddress(address);

        // upsert 存在就更新，不存在就插入
        UpdateResponse<EsUser> updateResponse =
            client.update(i -> i.index("willian_user").id("1").upsert(user), EsUser.class);

        log.info("更新文档结果:{}", updateResponse.version());
    }

    /**
     * 批量更新文档
     */
    @Test
    public void updateBatchDocumentTest() throws Exception {

        List<EsUser> users = this.getMockUsers();
        List<BulkOperation> bulkOperations = users.stream().map(user -> BulkOperation.of(
            // 指定操作的动作 ---> 更新操作
            b -> b.update(v ->
            // 指定更新的索引
            v.index("willian_user")
                // 指定更新的文档ID
                .id(String.valueOf(user.getId()))
                // 指定更新的文档内容
                .action(a -> a.doc(user)))))
            .collect(Collectors.toList());
        BulkResponse bulkResponse = client.bulk(i -> i.index("willian_user").operations(bulkOperations));
        for (BulkResponseItem item : bulkResponse.items()) {
            log.info("批量添加文档结果:{}:{}", item.id(), item.version());
        }
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocumentTest() throws Exception {

        DeleteResponse deleteResponse = client.delete(d -> d.index("willian_user").id("1"));
        log.info("删除文档结果：{}", deleteResponse.version());
    }

    /**
     * 批量删除文档
     */
    @Test
    public void batchDeleteDocumentTest() throws Exception {

        List<String> deleteIds = Lists.newArrayList("1", "2");
        List<BulkOperation> bulkOperations = deleteIds.stream().map(id -> BulkOperation.of(
            // 指定操作的动作 ---> 删除操作
            b -> b.delete(d ->
            // 指定删除的索引
            d.index("willian_user")
                // 指定删除的文档ID
                .id(id))))
            .collect(Collectors.toList());
        BulkResponse bulkResponse = client.bulk(i -> i.index("willian_user").operations(bulkOperations));
        for (BulkResponseItem item : bulkResponse.items()) {
            log.info("批量删除文档结果:{}:{}", item.id(), item.version());
        }
    }

    private List<EsUser> getMockUsers() {

        List<EsUser> result = Lists.newArrayList();

        EsAddress address1 = new EsAddress();
        address1.setProvince("浙江省");
        address1.setCity("杭州市");
        address1.setRegion("西湖区");
        address1.setDetail("东洲街道120号");

        EsAddress address2 = new EsAddress();
        address2.setProvince("浙江省");
        address2.setCity("杭州市");
        address2.setRegion("滨江区");
        address2.setDetail("滨江大道99号");

        EsAddress address3 = new EsAddress();
        address3.setProvince("四川省");
        address3.setCity("成都市");
        address3.setRegion("金牛区");
        address3.setDetail("成华街道10号");

        EsUser user1 = new EsUser();
        user1.setId(1);
        user1.setAge(22);
        user1.setName("林冲");
        user1.setIdCard("339900198807214455");
        user1.setPhone("15922359234");
        user1.setAddress(address1);

        EsUser user2 = new EsUser();
        user2.setId(2);
        user2.setAge(56);
        user2.setName("宋江");
        user2.setIdCard("339900199809104557");
        user2.setPhone("17824543124");
        user2.setAddress(address2);

        EsUser user3 = new EsUser();
        user3.setId(3);
        user3.setAge(30);
        user3.setName("武松");
        user3.setIdCard("339900200102134549");
        user3.setPhone("13477882356");
        user3.setAddress(address3);

        result.add(user1);
        result.add(user2);
        result.add(user3);
        return result;
    }
}
