package cn.willian.coding.leetcode.graph;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import lombok.Getter;

/**
 * 邻接表图
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:21:15
 */
public class GraphAdjList<E> implements IGraph<E> {

    /**
     * 顶点的最大数量
     */
    private final int maxNumOfVertex;
    /**
     * 顶点数组
     */
    private final VNode<E>[] vertexes;
    /**
     * 顶点的实际数量
     */
    @Getter
    private int numOfVertex;
    /**
     * 判断顶点是否被访问过
     */
    private boolean[] visited;

    @SuppressWarnings("unchecked")
    public GraphAdjList(int maxNumOfVertex) {
        this.maxNumOfVertex = maxNumOfVertex;
        vertexes = (VNode<E>[])Array.newInstance(VNode.class, maxNumOfVertex);
    }

    @Override
    public IGraph<E> createGraph() {
        return null;
    }

    @Override
    public boolean insertVertex(E v) {
        if (numOfVertex >= maxNumOfVertex)
            return false;
        VNode<E> vex = new VNode<E>();
        vex.data = v;
        vertexes[numOfVertex++] = vex;
        return true;
    }

    @Override
    public boolean deleteVertex(E v) {
        for (int i = 0; i < numOfVertex; i++) {
            if (vertexes[i].data.equals(v)) {
                for (int j = i; j < numOfVertex - 1; j++) {
                    vertexes[j] = vertexes[j + 1];
                }
                vertexes[numOfVertex - 1] = null;
                numOfVertex--;
                ENode current;
                ENode previous;
                for (int j = 0; j < numOfVertex; j++) {
                    if (vertexes[j].firstAdj == null)
                        continue;
                    if (vertexes[j].firstAdj.adjVex == i) {
                        vertexes[j].firstAdj = null;
                        continue;
                    }
                    current = vertexes[j].firstAdj;
                    while (current != null) {
                        previous = current;
                        current = current.nextAdj;
                        if (current != null && current.adjVex == i) {
                            previous.nextAdj = current.nextAdj;
                            break;
                        }
                    }
                }
                for (int j = 0; j < numOfVertex; j++) {
                    current = vertexes[j].firstAdj;
                    while (current != null) {
                        if (current.adjVex > i)
                            current.adjVex--;
                        current = current.nextAdj;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOfVertex(E v) {
        for (int i = 0; i < numOfVertex; i++) {
            if (vertexes[i].data.equals(v)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E valueOfVertex(int v) {
        if (v < 0 || v >= numOfVertex)
            return null;
        return vertexes[v].data;
    }

    @Override
    public boolean insertEdge(int v1, int v2, int weight) {
        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        ENode vex1 = new ENode(v2, weight);

        // 索引为index1的顶点没有邻接顶点
        if (vertexes[v1].firstAdj == null) {
            vertexes[v1].firstAdj = vex1;
        }
        // 索引为index1的顶点有邻接顶点
        else {
            vex1.nextAdj = vertexes[v1].firstAdj;
            vertexes[v1].firstAdj = vex1;
        }
        ENode vex2 = new ENode(v1, weight);
        // 索引为index2的顶点没有邻接顶点
        if (vertexes[v2].firstAdj == null) {
            vertexes[v2].firstAdj = vex2;
        }
        // 索引为index1的顶点有邻接顶点
        else {
            vex2.nextAdj = vertexes[v2].firstAdj;
            vertexes[v2].firstAdj = vex2;
        }
        return true;
    }

    @Override
    public boolean deleteEdge(int v1, int v2) {

        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        // 删除索引为index1的顶点与索引为index2的顶点之间的边
        ENode current = vertexes[v1].firstAdj;
        ENode previous = null;
        while (current != null && current.adjVex != v2) {
            previous = current;
            current = current.nextAdj;
        }
        if (current != null)
            previous.nextAdj = current.nextAdj;
        // 删除索引为index2的顶点与索引为index1的顶点之间的边
        current = vertexes[v2].firstAdj;
        while (current != null && current.adjVex != v1) {
            previous = current;
            current = current.nextAdj;
        }
        if (current != null)
            previous.nextAdj = current.nextAdj;
        return true;
    }

    @Override
    public int getEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        ENode current = vertexes[v1].firstAdj;
        while (current != null) {
            if (current.adjVex == v2) {
                return current.weight;
            }
            current = current.nextAdj;
        }
        return 0;
    }

    @Override
    public String depthFirstSearch(int v) {

        if (v < 0 || v >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        visited = new boolean[numOfVertex];
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        ENode current;
        while (!stack.isEmpty()) {
            v = stack.pop();
            sb.append(vertexes[v].data).append(",");
            current = vertexes[v].firstAdj;
            while (current != null) {
                if (!visited[current.adjVex]) {
                    stack.push(current.adjVex);
                    visited[current.adjVex] = true;
                }
                current = current.nextAdj;
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }

    @Override
    public String breadFirstSearch(int v) {

        if (v < 0 || v >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        visited = new boolean[numOfVertex];
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        ENode current;
        while (!queue.isEmpty()) {
            v = queue.poll();
            sb.append(vertexes[v].data).append(",");
            current = vertexes[v].firstAdj;
            while (current != null) {
                if (!visited[current.adjVex]) {
                    queue.offer(current.adjVex);
                    visited[current.adjVex] = true;
                }
                current = current.nextAdj;
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }

    @Override
    public int[] dijkstra(int v) {
        if (v < 0 || v >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        boolean[] st = new boolean[numOfVertex];// 默认初始为false
        int[] distance = new int[numOfVertex];// 存放源点到其他点的距离
        for (int i = 0; i < numOfVertex; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        ENode current;
        current = vertexes[v].firstAdj;
        while (current != null) {
            distance[current.adjVex] = current.weight;
            current = current.nextAdj;
        }
        distance[v] = 0;
        st[v] = true;
        // 处理从源点到其余顶点的最短路径
        for (int i = 0; i < numOfVertex; i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            // 比较从源点到其余顶点的路径长度
            for (int j = 0; j < numOfVertex; j++) {
                // 从源点到j顶点的最短路径还没有找到
                if (!st[j]) {
                    // 从源点到j顶点的路径长度最小
                    if (distance[j] < min) {
                        index = j;
                        min = distance[j];
                    }
                }
            }
            // 找到源点到索引为index顶点的最短路径长度
            if (index != -1)
                st[index] = true;
            // 更新当前最短路径及距离
            for (int w = 0; w < numOfVertex; w++)
                if (!st[w]) {
                    current = vertexes[w].firstAdj;
                    while (current != null) {
                        if (current.adjVex == index)
                            if ((min + current.weight) < distance[w]) {
                                distance[w] = min + current.weight;
                                break;
                            }
                        current = current.nextAdj;
                    }
                }
        }
        return distance;
    }

    /**
     * 邻接表中表的顶点
     */
    private static class VNode<E> {

        /**
         * 顶点信息
         */
        private E data;
        /**
         * 邻接表的第1个结点
         */
        private ENode firstAdj;
    }

    /**
     * 邻接表中表对应的链表的顶点
     */
    private static class ENode {

        /**
         * 边权值
         */
        private final int weight;
        /**
         * 邻接顶点序号
         */
        private int adjVex;
        /**
         * 下一个邻接表结点
         */
        private ENode nextAdj;

        public ENode(int adjVex, int weight) {
            this.adjVex = adjVex;
            this.weight = weight;
        }
    }
}
