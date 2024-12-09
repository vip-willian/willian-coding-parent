package cn.willian.coding.es;

import static cn.wiilian.coding.es.constants.IndexConstants.HOTEL_ALIAS;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.hutool.core.collection.CollectionUtil;
import cn.wiilian.coding.es.EsClientPool;
import cn.wiilian.coding.es.SearchResultPrints;
import cn.wiilian.coding.es.obj.EsHotel;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.DistanceUnit;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.ScrollResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;

/**
 * ES搜索结果处理相关操作
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-08 15:42:52
 */
@Slf4j
public class EsResultTest {

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
     * 排序
     */
    @Test
    public void searchResultSortTest() throws Exception {

        // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序
        SearchResponse<EsHotel> searchResponse =
            client.search(s -> s.index(HOTEL_ALIAS).query(q -> q.term(v -> v.field("brand").value("如家"))).sort(
                SortOptions.of(s1 -> s1
                    .geoDistance(v -> v.field("location").location(l -> l.latlon(x -> x.lat(31.034661).lon(121.612282)))
                        .order(SortOrder.Asc).unit(DistanceUnit.Kilometers))),
                SortOptions.of(s2 -> s2.field(v -> v.field("score").order(SortOrder.Desc))),
                SortOptions.of(s3 -> s3.field(v -> v.field("price").order(SortOrder.Asc)))), EsHotel.class);
        SearchResultPrints.print(searchResponse);
    }

    /**
     * 分页 From + Size 方式 【不能超过10000】
     */
    @Test
    public void searchResultPageWithFromSizeTest() throws Exception {

        int pageSize = 2;
        // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序 (第1页)
        SearchResponse<EsHotel> searchResponse1 = client.search(s -> s.index(HOTEL_ALIAS)
            // 查询条件
            .query(q -> q.term(v -> v.field("brand").value("如家")))
            // 分页
            .from(0).size(pageSize)
            // 排序
            .sort(
                SortOptions.of(s1 -> s1
                    .geoDistance(v -> v.field("location").location(l -> l.latlon(x -> x.lat(31.034661).lon(121.612282)))
                        .order(SortOrder.Asc).unit(DistanceUnit.Kilometers))),
                SortOptions.of(s2 -> s2.field(v -> v.field("score").order(SortOrder.Desc))),
                SortOptions.of(s3 -> s3.field(v -> v.field("price").order(SortOrder.Asc)))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse1);

        int pageNo = 2;

        // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序 (第2页)
        SearchResponse<EsHotel> searchResponse2 = client.search(s -> s.index(HOTEL_ALIAS)
            // 查询条件
            .query(q -> q.term(v -> v.field("brand").value("如家")))
            // 分页
            .from((pageNo - 1) * pageSize).size(pageSize)
            // 排序
            .sort(
                SortOptions.of(s1 -> s1
                    .geoDistance(v -> v.field("location").location(l -> l.latlon(x -> x.lat(31.034661).lon(121.612282)))
                        .order(SortOrder.Asc).unit(DistanceUnit.Kilometers))),
                SortOptions.of(s2 -> s2.field(v -> v.field("score").order(SortOrder.Desc))),
                SortOptions.of(s3 -> s3.field(v -> v.field("price").order(SortOrder.Asc)))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse2);
    }

    /**
     * 分页 Scroll 方式 【过时】
     */
    @Test
    public void searchResultPageWithScrollTest() throws Exception {

        int pageSize = 2;
        // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序 (第1页)
        SearchResponse<EsHotel> searchResponse1 = client.search(s -> s.index(HOTEL_ALIAS).scroll(sc -> sc.time("600s"))
            // 查询条件
            .query(q -> q.term(v -> v.field("brand").value("如家")))
            // 一页条数
            .size(pageSize)
            // 排序
            .sort(
                SortOptions.of(s1 -> s1
                    .geoDistance(v -> v.field("location").location(l -> l.latlon(x -> x.lat(31.034661).lon(121.612282)))
                        .order(SortOrder.Asc).unit(DistanceUnit.Kilometers))),
                SortOptions.of(s2 -> s2.field(v -> v.field("score").order(SortOrder.Desc))),
                SortOptions.of(s3 -> s3.field(v -> v.field("price").order(SortOrder.Asc)))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse1);

        // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序 (第2页)
        ScrollResponse<EsHotel> scrollResponse =
            client.scroll(s -> s.scrollId(searchResponse1.scrollId()).scroll(sc -> sc.time("600s")), EsHotel.class);
        SearchResultPrints.print(scrollResponse);
    }

    /**
     * 分页 Scroll After方式 【推荐方式】
     */
    @Test
    public void searchResultPageWithScrollAfterTest() throws Exception {

        int pageSize = 2;
        // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序 (第1页)
        SearchResponse<EsHotel> searchResponse1 = client.search(s -> s.index(HOTEL_ALIAS)
            // 查询条件
            .query(q -> q.term(v -> v.field("brand").value("如家")))
            // 一页条数
            .from(0).size(pageSize)
            // 排序
            .sort(
                SortOptions.of(s1 -> s1
                    .geoDistance(v -> v.field("location").location(l -> l.latlon(x -> x.lat(31.034661).lon(121.612282)))
                        .order(SortOrder.Asc).unit(DistanceUnit.Kilometers))),
                SortOptions.of(s2 -> s2.field(v -> v.field("score").order(SortOrder.Desc))),
                SortOptions.of(s3 -> s3.field(v -> v.field("price").order(SortOrder.Asc)))),
            EsHotel.class);
        SearchResultPrints.print(searchResponse1);

        if (searchResponse1.hits() != null && CollectionUtil.isNotEmpty(searchResponse1.hits().hits())) {
            List<Hit<EsHotel>> hits = searchResponse1.hits().hits();
            Hit<EsHotel> lastData = hits.get(hits.size() - 1);
            // 查询酒店品牌为如家的酒店，按距离最近、评分最高、价格最低排序 (第2页)
            SearchResponse<EsHotel> searchResponse2 = client.search(s -> s.index(HOTEL_ALIAS)
                // 查询条件
                .query(q -> q.term(v -> v.field("brand").value("如家")))
                // 一页条数
                .from(0).size(pageSize)
                // 上一页最后一条的sort数据
                .searchAfter(lastData.sort())
                // 排序
                .sort(
                    SortOptions.of(s1 -> s1.geoDistance(
                        v -> v.field("location").location(l -> l.latlon(x -> x.lat(31.034661).lon(121.612282)))
                            .order(SortOrder.Asc).unit(DistanceUnit.Kilometers))),
                    SortOptions.of(s2 -> s2.field(v -> v.field("score").order(SortOrder.Desc))),
                    SortOptions.of(s3 -> s3.field(v -> v.field("price").order(SortOrder.Asc)))),
                EsHotel.class);
            SearchResultPrints.print(searchResponse2);
        }
    }

    /**
     * 高亮
     */
    @Test
    public void searchResultHighlightTest() throws Exception {

        SearchResponse<EsHotel> searchResponse = client.search(
            s -> s.query(m -> m.match(v -> v.field("all").query("如家")))
                .highlight(v -> v.fields("name", h -> h.requireFieldMatch(false).preTags("<em>").postTags("</em>"))),
            EsHotel.class);
        SearchResultPrints.printHighlight(searchResponse);
    }
}
