package cn.willian.coding.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:35:45
 */
public class Graph<E> {

    /**
     * 节点信息
     */
    public Map<Integer, Vertex<E>> nodes;
    /**
     * 边信息
     */
    public Set<Edge<E>> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    @Data
    public static class Vertex<E> {

        /**
         * 图节点值
         */
        public E value;
        /**
         * 入度数量
         */
        public int in;
        /**
         * 出度数量
         */
        public int out;
        /**
         * 图节点指向下一系列节点
         */
        public List<Vertex<E>> nextList;
        /**
         * 图节点拥有的边数量
         */
        public List<Vertex<E>> edgeList;

        public Vertex(E value) {
            this.value = value;
            in = 0;
            out = 0;
            nextList = new ArrayList<>();
            edgeList = new ArrayList<>();
        }
    }

    @Data
    public static class Edge<E> {

        /**
         * 边占用的权重
         */
        public int weight;
        /**
         * 边开始节点
         */
        public Vertex<E> from;
        /**
         * 边到达节点
         */
        public Vertex<E> to;

        public Edge(int weight, Vertex<E> from, Vertex<E> to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}
