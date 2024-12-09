package cn.willian.coding.es;

import static cn.wiilian.coding.es.constants.IndexConstants.HOTEL_ALIAS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.wiilian.coding.es.EsClientPool;
import cn.wiilian.coding.es.obj.EsHotel;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationRange;
import co.elastic.clients.elasticsearch._types.aggregations.CalendarInterval;
import co.elastic.clients.elasticsearch._types.aggregations.DateHistogramBucket;
import co.elastic.clients.elasticsearch._types.aggregations.HistogramBucket;
import co.elastic.clients.elasticsearch._types.aggregations.RangeBucket;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.util.NamedValue;
import lombok.extern.slf4j.Slf4j;

/**
 * ES聚合相关操作
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 22:58:46
 */
@Slf4j
public class EsAggregationTest {

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
     * 值聚合
     */
    @Test
    public void valueCountTest() throws Exception {

        // 统计城市为上海的酒店数量
        SearchResponse<EsHotel> searchResponse =
            client.search(i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                .aggregations("totalDoc", a -> a.valueCount(f -> f.field("id"))), EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate totalDoc = aggregations.get("totalDoc");
        log.info("totalDoc =  {}", totalDoc.valueCount().value());
    }

    /**
     * 基数聚合
     */
    @Test
    public void cardinalityTest() throws Exception {

        // 统计城市为上海不同价格的酒店数量
        SearchResponse<EsHotel> searchResponse =
            client.search(i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                .aggregations("priceTotalDoc", a -> a.cardinality(f -> f.field("price"))), EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate totalDoc = aggregations.get("priceTotalDoc");
        log.info("priceTotalDoc =  {}", totalDoc.cardinality().value());
    }

    /**
     * 求和、平均、最大、最小
     */
    @Test
    public void sumOrAvgOrMaxOrMinTest() throws Exception {

        // 统计城市为上海 总价、平均价、最高价、最低价
        Map<String, Aggregation> map = new HashMap<>();
        map.put("totalPrice", Aggregation.of(v -> v.sum(f -> f.field("price"))));
        map.put("avgPrice", Aggregation.of(v -> v.avg(f -> f.field("price"))));
        map.put("maxPrice", Aggregation.of(v -> v.max(f -> f.field("price"))));
        map.put("minPrice", Aggregation.of(v -> v.min(f -> f.field("price"))));

        SearchResponse<EsHotel> searchResponse = client.search(
            i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0).aggregations(map),
            EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        aggregations.forEach((name, v) -> {
            if (v.isSum()) {
                log.info("{} =  {}", name, v.sum().value());
            } else if (v.isAvg()) {
                log.info("{} =  {}", name, v.avg().value());
            } else if (v.isMax()) {
                log.info("{} =  {}", name, v.max().value());
            } else if (v.isMin()) {
                log.info("{} =  {}", name, v.min().value());
            }
        });
    }

    /**
     * Terms聚合
     */
    @Test
    public void termsTest() throws Exception {

        // 城市为上海 按星级分组
        SearchResponse<EsHotel> searchResponse =
            client.search(i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                // 分组聚合
                .aggregations("starGroup", a -> a.terms(f -> f.field("starName"))), EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate starGroup = aggregations.get("starGroup");
        List<StringTermsBucket> buckets = starGroup.sterms().buckets().array();
        for (StringTermsBucket bucket : buckets) {
            log.info("分组KEY: {}: 文档数量：{}", bucket.key().stringValue(), bucket.docCount());
        }
    }

    /**
     * Histogram聚合
     */
    @Test
    public void histogramTest() throws Exception {

        // 城市为上海 按价格区间分组
        SearchResponse<EsHotel> searchResponse = client.search(
            i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                // 分组聚合
                .aggregations("priceRangeGroup", a -> a.histogram(f -> f.field("price").interval(300d))),
            EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate priceRangeGroup = aggregations.get("priceRangeGroup");
        List<HistogramBucket> buckets = priceRangeGroup.histogram().buckets().array();
        for (HistogramBucket bucket : buckets) {
            log.info("分组KEY: {}: 文档数量：{}", bucket.key(), bucket.docCount());
        }
    }

    /**
     * Date Histogram聚合
     */
    @Test
    public void dateHistogramTest() throws Exception {

        // 城市为上海 酒店成立时间分组
        SearchResponse<EsHotel> searchResponse = client.search(
            i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                // 分组聚合
                .aggregations("createTimeGroup",
                    a -> a.dateHistogram(
                        f -> f.field("createdTime").calendarInterval(CalendarInterval.Month).format("yyyy-MM-dd"))),
            EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate createTimeGroup = aggregations.get("createTimeGroup");
        List<DateHistogramBucket> buckets = createTimeGroup.dateHistogram().buckets().array();
        for (DateHistogramBucket bucket : buckets) {
            log.info("分组KEY: {}: 文档数量：{}", bucket.key(), bucket.docCount());
        }
    }

    /**
     * Range 聚合
     */
    @Test
    public void rangeTest() throws Exception {

        SearchResponse<EsHotel> searchResponse =
            client.search(i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                // 分组聚合
                .aggregations("priceRangeGroup",
                    a -> a.range(f -> f.field("price").ranges(AggregationRange.of(r -> r.key("lt500").to("500")),
                        AggregationRange.of(r -> r.key("500And1000").from("500").to("1000")),
                        AggregationRange.of(r -> r.key("gt1000").from("1000"))))),
                EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate priceRangeGroup = aggregations.get("priceRangeGroup");
        List<RangeBucket> buckets = priceRangeGroup.range().buckets().array();
        for (RangeBucket bucket : buckets) {
            log.info("分组KEY: {}: from:{},to:{},文档数量：{}", bucket.key(), bucket.from(), bucket.to(), bucket.docCount());
        }
    }

    /**
     * _count聚合排序
     */
    @Test
    @SuppressWarnings("all")
    public void _countSortTest() throws Exception {

        // 城市为上海 按价格区间分组
        SearchResponse<EsHotel> searchResponse = client.search(
            i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0)
                // 分组聚合
                .aggregations("priceRangeGroup",
                    a -> a.histogram(
                        f -> f.field("price").interval(300d).order(NamedValue.of("_count", SortOrder.Desc)))),
            EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate priceRangeGroup = aggregations.get("priceRangeGroup");
        List<HistogramBucket> buckets = priceRangeGroup.histogram().buckets().array();
        for (HistogramBucket bucket : buckets) {
            log.info("分组KEY: {}: 文档数量：{}", bucket.key(), bucket.docCount());
        }
    }

    /**
     * _key聚合排序
     */
    @Test
    @SuppressWarnings("all")
    public void _keySortTest() throws Exception {

        // 城市为上海 按星级分组
        SearchResponse<EsHotel> searchResponse = client.search(
            i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0).aggregations(
                "starGroup", a -> a.terms(f -> f.field("starName").order(NamedValue.of("_key", SortOrder.Asc)))),
            EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate starGroup = aggregations.get("starGroup");
        List<StringTermsBucket> buckets = starGroup.sterms().buckets().array();
        for (StringTermsBucket bucket : buckets) {
            log.info("分组KEY: {}: 文档数量：{}", bucket.key().stringValue(), bucket.docCount());
        }
    }

    /**
     * _term聚合排序
     */
    @Test
    public void _termSortTest() throws Exception {

        // 暂无案例
    }

    /**
     * 度量聚合排序
     */
    @Test
    @SuppressWarnings("all")
    public void metricsSortTest() throws Exception {

        // 城市为上海 按品牌分组，按品牌的平均价格排序
        SearchResponse<EsHotel> searchResponse = client
            .search(i -> i.index(HOTEL_ALIAS).query(q -> q.term(c -> c.field("city").value("上海"))).size(0).aggregations(
                "brandGroup", a -> a.terms(f -> f.field("brand").order(NamedValue.of("avgPrice", SortOrder.Asc)))
                    .aggregations("avgPrice", p -> p.avg(f -> f.field("price")))),
                EsHotel.class);
        Map<String, Aggregate> aggregations = searchResponse.aggregations();
        Aggregate brandGroup = aggregations.get("brandGroup");
        List<StringTermsBucket> buckets = brandGroup.sterms().buckets().array();
        for (StringTermsBucket bucket : buckets) {
            log.info("分组KEY: {}: 文档数量：{}, 平均价格：{}", bucket.key().stringValue(), bucket.docCount(),
                bucket.aggregations().get("avgPrice").avg().value());
        }
    }

}
