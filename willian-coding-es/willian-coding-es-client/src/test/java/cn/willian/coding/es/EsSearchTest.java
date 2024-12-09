package cn.willian.coding.es;

import static cn.wiilian.coding.es.constants.IndexConstants.HOTEL_ALIAS;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

import cn.wiilian.coding.es.EsClientPool;
import cn.wiilian.coding.es.SearchResultPrints;
import cn.wiilian.coding.es.obj.EsHotel;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.GeoLocation;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionBoostMode;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import lombok.extern.slf4j.Slf4j;

/**
 * ES搜索相关操作
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-08 15:42:52
 */
@Slf4j
public class EsSearchTest {

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
     * 搜索全部文档
     */
    @Test
    public void searchAllTest() throws Exception {

        // 默认只返回10条数据
        SearchResponse<EsHotel> searchResponse = client.search(s ->
        // 指定查询的文档
        s.index(HOTEL_ALIAS).
        // 设置查询条件
            query(q -> q.matchAll(m -> m)), EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 匹配单个字段
     */
    @Test
    public void searchMatchTest() throws Exception {

        // 查询酒店名称含有"连锁"的数据
        SearchResponse<EsHotel> searchResponse = client.search(s ->
        // 指定查询的文档
        s.index(HOTEL_ALIAS).
        // 设置查询字段名称为name,值为"连锁"
            query(q -> q.match(c -> c.field("name").query("连锁"))), EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 匹配多个字段
     */
    @Test
    public void searchMultiMatchTest() throws Exception {

        // 查询酒店名称、城市含有"连锁或北京"的数据
        SearchResponse<EsHotel> searchResponse = client.search(s ->
        // 指定查询的文档
        s.index(HOTEL_ALIAS).query(q -> q.multiMatch(c -> c.query("北京连锁").fields("name", "city"))), EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 精确匹配单个字段
     */
    @Test
    public void searchTermTest() throws Exception {

        // 查询城市等于上海的数据
        SearchResponse<EsHotel> searchResponse = client
            .search(s -> s.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))), EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * terms实现SQL的in语句
     */
    @Test
    public void searchTermsTest() throws Exception {

        // 查询品牌 等于【"速8"，"7天酒店"】的 数据
        SearchResponse<EsHotel> searchResponse = client.search(
            s -> s.index(HOTEL_ALIAS)
                .query(q -> q.terms(c -> c.field("brand")
                    .terms(v -> v.value(Lists.newArrayList(FieldValue.of("7天酒店"), FieldValue.of("速8")))))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 范围查询
     */
    @Test
    public void searchRangeTest() throws Exception {

        // 查询 价格 大于等于100 小于等于300 的 数据
        SearchResponse<EsHotel> searchResponse =
            client.search(
                s -> s.index(HOTEL_ALIAS)
                    .query(q -> q.range(c -> c.field("price").gte(JsonData.of(100)).lte(JsonData.of(300)))),
                EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 地理坐标查询-圆形范围查询
     */
    @Test
    public void searchGeoDistanceTest() throws Exception {

        // 查询附近5公里的酒店
        SearchResponse<EsHotel> searchResponse =
            client.search(
                s -> s.index(HOTEL_ALIAS)
                    .query(q -> q.geoDistance(c -> c.distance("5km").field("location")
                        .location(GeoLocation.of(v -> v.latlon(x -> x.lat(39.896449).lon(116.317382)))))),
                EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 地理坐标查询-矩形范围查询
     */
    @Test
    public void searchGeoBoundingBoxTest() throws Exception {

        // 查询在这2个坐标点范围下的所有酒店
        SearchResponse<EsHotel> searchResponse = client.search(s -> s.index(HOTEL_ALIAS).query(q -> q
            // 设置根据哪个字段查询
            .geoBoundingBox(c -> c.field("location")
                // 设置左上、右下经纬度
                .boundingBox(b -> b.tlbr(x -> x.topLeft(tl -> tl.latlon(m -> m.lat(39.896449).lon(116.317382)))
                    .bottomRight(br -> br.latlon(n -> n.lat(39.864026).lon(116.322505))))))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 复合查询
     */
    @Test
    public void searchBoolTest() throws Exception {

        // 查询在城市为上海名称包含如家、等级为二钻 或 三钻 且 价格不超过1000的酒店
        SearchResponse<EsHotel> searchResponse = client.search(i -> i.index(HOTEL_ALIAS)
            // 城市为上海
            .query(q -> q.bool(b -> b.filter(c -> c.term(f -> f.field("city").value("上海")))
                // 酒店名称包含如家
                .must(m -> m.match(f -> f.field("name").query("如家")))
                // 等级为二钻 或 三钻
                .should(s -> s.terms(f -> f.field("starName")
                    .terms(v -> v.value(Lists.newArrayList(FieldValue.of("二钻"), FieldValue.of("三钻"))))))
                // 价格不超过1000
                .mustNot(mn -> mn.range(f -> f.field("price").gte(JsonData.of(1000)))))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 算分查询
     */
    @Test
    public void searchFunctionScoreTest() throws Exception {

        // 查询酒店名称含有“外滩”，并给品牌为“如家”的权重升级
        SearchResponse<EsHotel> searchResponse = client.search(i -> i.index(HOTEL_ALIAS)
            .query(q -> q.functionScore(x -> x.query(s -> s.match(v -> v.field("name").query("外滩")))
                .functions(f -> f.filter(s -> s.term(v -> v.field("brand").value("如家"))).weight(10d))
                .boostMode(FunctionBoostMode.Multiply))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }
}
