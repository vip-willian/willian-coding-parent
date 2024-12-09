package cn.wiilian.coding.es;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;

import com.google.common.base.Throwables;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

/**
 * 使用对象池，复用资源
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 09:52:10
 */
public class EsClientPoolFactory implements PooledObjectFactory<ElasticsearchClient> {

    private static final String serverUrl = "https://localhost:9200";
    private static final String apiKey = "TkZFM3BaTUItZjluYUZ4VHFFRGk6dEF3elNZZmRRUjJUTUpfeGFlaEVOdw==";
    private static final String username = "elastic";
    private static final String password = "123456";

    public static void close(ElasticsearchClient client) {

        try {
            if (client != null) {
                client.shutdown();
            }
        } catch (Exception e) {
            System.out.println("关闭客户端链接失败：{}" + Throwables.getStackTraceAsString(e));
        }
    }

    @Override
    public void activateObject(PooledObject<ElasticsearchClient> pooledObject) throws Exception {

    }

    @Override
    public void destroyObject(PooledObject<ElasticsearchClient> pooledObject) throws Exception {

        ElasticsearchClient client = pooledObject.getObject();
        close(client);
    }

    @Override
    public PooledObject<ElasticsearchClient> makeObject() throws Exception {
        return new DefaultPooledObject<>(getClient1());
    }

    @Override
    public void passivateObject(PooledObject<ElasticsearchClient> pooledObject) throws Exception {

    }

    @Override
    public boolean validateObject(PooledObject<ElasticsearchClient> pooledObject) {
        return true;
    }

    // 方式1
    public ElasticsearchClient getClient1() {

        // 1、Create the low-level client
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClient restClient = RestClient.builder(HttpHost.create(serverUrl)).setCompressionEnabled(true)
            .setHttpClientConfigCallback(httpClientBuilder -> {
                // 这里设置鉴权需要的用户名elastic和对应密码
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                SSLContextBuilder sscb = SSLContexts.custom();
                try {
                    sscb.loadTrustMaterial((chain, authType) -> {
                        // 在这里跳过证书信息校验
                        // System.out.println("isTrusted|" + authType + "|" + Arrays.toString(chain));
                        return true;
                    });
                } catch (NoSuchAlgorithmException | KeyStoreException e) {
                    System.out.println(Throwables.getStackTraceAsString(e));
                }
                try {
                    httpClientBuilder.setSSLContext(sscb.build());
                } catch (KeyManagementException | NoSuchAlgorithmException e) {
                    System.out.println(Throwables.getStackTraceAsString(e));
                }
                // 这里跳过主机名称校验
                httpClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
                return httpClientBuilder;
            }).build();

        // 2、Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // 3、And create the API client
        return new ElasticsearchClient(transport);
    }

    // 方式2
    public ElasticsearchClient getClient2() {

        // 1、Create the low-level client
        RestClient restClient = RestClient.builder(HttpHost.create(serverUrl))
            .setDefaultHeaders(new Header[] {new BasicHeader("Authorization", "ApiKey" + apiKey)}).build();

        // 2、Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // 3、And create the API client
        return new ElasticsearchClient(transport);
    }
}
