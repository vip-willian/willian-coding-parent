package cn.wiilian.coding.es;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 18:06:56
 */
@Slf4j
@UtilityClass
public class SearchResultPrints {

    public static <T> void print(ResponseBody<T> response) {

        // 查询没有超时
        if (response.timedOut()) {
            return;
        }
        // 获取整体查询结果
        HitsMetadata<T> result = response.hits();
        if (result != null) {
            // 查询的总数据条数，从hits.total解析
            TotalHits total = result.total();
            if (total != null) {
                log.info("查询数据总条数为:{}", total.value());
            }
            // 查询的数据，从hits.hits解析
            List<Hit<T>> hits = result.hits();
            for (Hit<T> hit : hits) {
                log.info("数据得分【Query Score】= 【{}】, 数据信息 =  {}", hit.score(), JSON.toJSONString(hit.source()));
            }
        }
    }

    public static <T> void printHighlight(ResponseBody<T> response) {

        // 查询没有超时
        if (response.timedOut()) {
            return;
        }
        // 获取整体查询结果
        HitsMetadata<T> result = response.hits();
        if (result != null) {
            // 查询的数据，从hits.hits解析
            List<Hit<T>> hits = result.hits();
            for (Hit<T> hit : hits) {
                Map<String, List<String>> highlight = hit.highlight();
                highlight.forEach((fileName, content) -> {
                    log.info("高亮字段 =【{}】, 高亮内容 =  {}", fileName, JSON.toJSONString(content));
                });
            }
        }
    }
}
