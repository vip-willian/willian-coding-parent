package cn.willian.coding;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-17 23:53:18
 */
public class ElasticIndexDemo {

    public static void main(String[] args) throws IOException {

        // 创建低级客户端
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        // 使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        // 创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

        // 创建hotel索引
        // CreateIndexResponse response = client.indices().create(c -> c.index("hotel").s );
        // System.out.println(response.acknowledged());
    }
}
