package cn.willian.coding.leetcode.graph;

import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:35:45
 */
@SuppressWarnings("all")
public class GraphFactory {

    public static <E> IGraph<E> createGraph(GraphTypeEnum graphType, List<E> vertexes, Map<Key<E, E>, Integer> edgeMap,
        Class<E> type) {

        if (CollectionUtil.isEmpty(vertexes) || MapUtil.isEmpty(edgeMap)) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 创建图数据结构
        IGraph<E> graph = getGraph(graphType, vertexes, type);
        // 插入顶点
        for (E vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        // 插入边数据
        edgeMap.forEach((vertex, weight) -> {
            int v1 = graph.indexOfVertex(vertex.getForm());
            int v2 = graph.indexOfVertex(vertex.getTo());
            graph.insertEdge(v1, v2, weight);
        });
        return graph;
    }

    private static <E> IGraph<E> getGraph(GraphTypeEnum graphType, List<E> vertexes, Class<E> type) {

        switch (graphType) {
            case ADJ_LIST:
                return new GraphAdjList<>(vertexes.size());
            case ADJ_MATRIX:
                return new GraphAdjMatrix<>(vertexes.size(), type);
            default:
                return new Graph<>();
        }
    }

    @Data
    static class Key<F, T> {
        private F form;
        private T to;

        public static <F, T> Key<F, T> of(F form, T to) {
            Key<F, T> key = new Key<>();
            key.setForm(form);
            key.setTo(to);
            return key;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass())
                return false;
            Key<?, ?> key = (Key<?, ?>)object;
            return Objects.equal(form, key.form) && Objects.equal(to, key.to);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(form, to);
        }
    }
}
