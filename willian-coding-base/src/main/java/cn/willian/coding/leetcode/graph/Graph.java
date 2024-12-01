package cn.willian.coding.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:35:45
 */
@SuppressWarnings("all")
public class Graph<E> implements IGraph<E> {

    /**
     * 节点信息
     */
    public Map<Integer, Vertex<E>> nodes;
    /**
     * 边信息
     */
    public Set<Edge<E>> edges;
    /**
     * 顶点的实际数量
     */
    @Getter
    private int numOfVertex;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
        this.numOfVertex = 0;
    }

    @Override
    public boolean insertVertex(E element) {

        int index = indexOfVertex(element);
        if (index == -1) {
            Vertex<E> vex = new Vertex<>(element);
            nodes.put(numOfVertex++, vex);
        }
        return true;
    }

    @Override
    public boolean deleteVertex(E element) {

        // 找到需要删除的顶点
        int index = indexOfVertex(element);
        if (index != -1) {
            // 获取需要删除的顶点数据
            Vertex<E> removeVertex = nodes.remove(index);
            numOfVertex--;
            // 移除顶点相关的边信息
            edges.removeIf(edge -> edge.getFrom().equals(removeVertex) || edge.getTo().equals(removeVertex));
        }
        return true;
    }

    @Override
    public int indexOfVertex(E v) {

        for (Map.Entry<Integer, Vertex<E>> entry : nodes.entrySet()) {
            if (entry.getValue().value.equals(v)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public E valueOfVertex(int v) {

        return nodes.get(v).value;
    }

    @Override
    public boolean insertEdge(int v1, int v2, int weight) {

        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        Vertex<E> node1 = nodes.get(v1);
        Vertex<E> node2 = nodes.get(v2);
        if (node1 == null || node2 == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // 从node1 指向 node2
        Edge<E> edge = new Edge<>(weight, node1, node2);

        // 节点1的出度增加
        node1.out++;
        // 节点2的入度增加
        node2.in++;

        node1.nextList.add(node2);
        node1.getEdgeList().add(edge);

        edges.add(edge);
        return true;
    }

    @Override
    public boolean deleteEdge(int v1, int v2) {

        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        Vertex<E> node1 = nodes.get(v1);
        Vertex<E> node2 = nodes.get(v2);
        if (node1 == null || node2 == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (Edge<E> edge : edges) {
            if (edge.getFrom().equals(node1) && edge.getTo().equals(node2)) {
                edges.remove(edge);
                node1.out--;
                node2.in--;
                node1.nextList.remove(node2);
                node1.edgeList.remove(edge);
            }
        }
        return true;
    }

    @Override
    public int getEdge(int v1, int v2) {

        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        Vertex<E> node1 = nodes.get(v1);
        Vertex<E> node2 = nodes.get(v2);
        if (node1 == null || node2 == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (Edge<E> edge : edges) {
            if (edge.getFrom().equals(node1) && edge.getTo().equals(node2)) {
                return edge.weight;
            }
        }
        return 0;
    }

    @Override
    public String depthFirstSearch(int v) {

        Vertex<E> node = nodes.get(v);
        if (node == null) {
            return "";
        }
        Stack<Vertex<E>> stack = new Stack<>();
        Set<Vertex<E>> visited = new HashSet<>();
        stack.push(node);
        visited.add(node);
        StringBuilder sb = new StringBuilder();
        sb.append(node.value).append(",");
        while (!stack.isEmpty()) {
            Vertex<E> cur = stack.pop();
            for (Vertex<E> next : cur.nextList) {
                if (!visited.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    visited.add(next);
                    sb.append(next.value).append(",");
                    break;
                }
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }

    @Override
    public String breadFirstSearch(int v) {

        Vertex<E> node = nodes.get(v);
        if (node == null) {
            return "";
        }
        Queue<Vertex<E>> queue = new LinkedList<>();
        Set<Vertex<E>> visited = new HashSet<>();
        queue.add(node);
        visited.add(node);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Vertex<E> cur = queue.poll();
            sb.append(cur.value).append(",");
            for (Vertex<E> next : cur.nextList) {
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }

    @Override
    public int[] dijkstra(int v) {

        Vertex<E> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        Map<Vertex<E>, Integer> distanceMap = new HashMap<>();
        Set<Vertex<E>> selectedVertices = new HashSet<>();

        distanceMap.put(node, 0);

        Vertex<E> minVertex = getMinDistanceAndUnSelectedVertex(distanceMap, selectedVertices);
        while (minVertex != null) {
            Integer minDistance = distanceMap.get(minVertex);
            for (Edge<E> edge : minVertex.getEdgeList()) {
                Vertex<E> toVertex = edge.getTo();
                if (!distanceMap.containsKey(toVertex)) {
                    distanceMap.put(toVertex, minDistance + edge.getWeight());
                }
                distanceMap.put(toVertex, Math.min(distanceMap.get(toVertex), minDistance + edge.getWeight()));
            }
            selectedVertices.add(minVertex);
            minVertex = getMinDistanceAndUnSelectedVertex(distanceMap, selectedVertices);
        }
        return new int[0];
    }

    private Vertex<E> getMinDistanceAndUnSelectedVertex(Map<Vertex<E>, Integer> distanceMap,
        Set<Vertex<E>> selectedVertices) {

        Vertex<E> minDistanceVertex = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Vertex<E>, Integer> entry : distanceMap.entrySet()) {
            Vertex<E> vertex = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedVertices.contains(vertex) && distance < minDistance) {
                minDistance = distance;
                minDistanceVertex = vertex;
            }
        }
        return minDistanceVertex;
    }

    @Getter
    @Setter
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
        public List<Edge<E>> edgeList;

        public Vertex(E value) {
            this.value = value;
            in = 0;
            out = 0;
            nextList = new ArrayList<>();
            edgeList = new ArrayList<>();
        }
    }

    @Getter
    @Setter
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
