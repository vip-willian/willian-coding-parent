package cn.willian.coding.leetcode.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import cn.willian.coding.leetcode.graph.GraphFactory.Key;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 18:21:17
 */
public class GraphTest {

    public static void main(String[] args) {

        // 构建图的顶点
        List<String> vertexes = new ArrayList<>();
        vertexes.add("A");
        vertexes.add("B");
        vertexes.add("C");
        vertexes.add("D");
        vertexes.add("E");
        vertexes.add("F");

        // 构建图的边
        Map<Key<String, String>, Integer> edgeMap = Maps.newHashMap();
        edgeMap.put(Key.of("A", "B"), 4);
        edgeMap.put(Key.of("A", "C"), 1);
        edgeMap.put(Key.of("B", "E"), 7);
        edgeMap.put(Key.of("B", "D"), 3);
        edgeMap.put(Key.of("C", "E"), 2);
        edgeMap.put(Key.of("C", "F"), 8);
        edgeMap.put(Key.of("E", "D"), 5);
        edgeMap.put(Key.of("F", "D"), 6);

        IGraph<String> graph1 = GraphFactory.createGraph(GraphTypeEnum.ADJ_MATRIX, vertexes, edgeMap, String.class);
        String breadFirstSearch1 = graph1.breadFirstSearch(0);
        System.out.println("邻接矩阵图-宽度优先遍历结果：" + breadFirstSearch1);

        String depthFirstSearch1 = graph1.depthFirstSearch(0);
        System.out.println("邻接矩阵图-深度优先遍历结果：" + depthFirstSearch1);

        IGraph<String> graph2 = GraphFactory.createGraph(GraphTypeEnum.ADJ_LIST, vertexes, edgeMap, String.class);
        String breadFirstSearch2 = graph2.breadFirstSearch(0);
        System.out.println("邻接表图-宽度优先遍历结果：" + breadFirstSearch2);

        String depthFirstSearch2 = graph2.depthFirstSearch(0);
        System.out.println("邻接表图-深度优先遍历结果：" + depthFirstSearch2);

        IGraph<String> graph3 = GraphFactory.createGraph(GraphTypeEnum.CUSTOM, vertexes, edgeMap, String.class);
        String breadFirstSearch3 = graph3.breadFirstSearch(0);
        System.out.println("自定义图-宽度优先遍历结果：" + breadFirstSearch3);

        String depthFirstSearch3 = graph3.depthFirstSearch(0);
        System.out.println("自定义图-深度优先遍历结果：" + depthFirstSearch3);

    }
}
