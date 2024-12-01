package cn.willian.coding.leetcode.graph;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import lombok.Getter;

/**
 * 邻接矩阵图
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:21:15
 */
@SuppressWarnings("all")
public class GraphAdjMatrix<E> implements IGraph<E> {

    /**
     * 存储图顶点的一维数组
     */
    private final E[] vertexes;
    /**
     * 存储图边的二维数组
     */
    private final int[][] edges;
    /**
     * 顶点的最大数量
     */
    private final int maxNumOfVertex;
    /**
     * 顶点数量
     */
    @Getter
    private int numOfVertex;
    /**
     * 判断顶点是否被访问过
     */
    private boolean[] visited;

    @SuppressWarnings("unchecked")
    public GraphAdjMatrix(int maxNumOfVertex, Class<E> type) {
        this.maxNumOfVertex = maxNumOfVertex;
        edges = new int[maxNumOfVertex][maxNumOfVertex];
        vertexes = (E[])Array.newInstance(type, maxNumOfVertex);
    }

    @Override
    public boolean insertVertex(E v) {

        if (numOfVertex >= maxNumOfVertex)
            return false;
        vertexes[numOfVertex++] = v;
        return true;
    }

    @Override
    public boolean deleteVertex(E v) {

        for (int i = 0; i < numOfVertex; i++) {
            if (vertexes[i].equals(v)) {
                // 元素全部往前移
                for (int j = i; j < numOfVertex - 1; j++) {
                    vertexes[j] = vertexes[j + 1];
                }
                // 清空最后一个元素数据
                vertexes[numOfVertex - 1] = null;

                // 删除对应边信息
                for (int col = i; col < numOfVertex - 1; col++) {
                    for (int row = 0; row < numOfVertex; row++) {
                        edges[col][row] = edges[col + 1][row];
                    }
                }
                // 删除对应边信息
                for (int row = i; row < numOfVertex - 1; row++) {
                    for (int col = 0; col < numOfVertex; col++) {
                        edges[col][row] = edges[col][row + 1];
                    }
                }
                numOfVertex--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOfVertex(E v) {
        for (int i = 0; i < numOfVertex; i++) {
            if (vertexes[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E valueOfVertex(int v) {
        if (v < 0 || v >= numOfVertex)
            return null;
        return vertexes[v];
    }

    @Override
    public boolean insertEdge(int v1, int v2, int weight) {

        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        return true;
    }

    @Override
    public boolean deleteEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        edges[v1][v2] = 0;
        edges[v2][v1] = 0;
        return true;
    }

    @Override
    public int getEdge(int v1, int v2) {

        if (v1 < 0 || v2 < 0 || v1 >= numOfVertex || v2 >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        return edges[v1][v2];
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
        while (!stack.isEmpty()) {
            v = stack.pop();
            sb.append(vertexes[v]).append(",");
            for (int i = numOfVertex - 1; i >= 0; i--) {
                if ((edges[v][i] != 0 && edges[v][i] != Integer.MAX_VALUE) && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
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
        while (!queue.isEmpty()) {
            v = queue.poll();
            sb.append(vertexes[v]).append(",");
            for (int i = 0; i < numOfVertex; i++) {
                if ((edges[v][i] != 0 && edges[v][i] != Integer.MAX_VALUE) && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : null;
    }

    @Override
    public int[] dijkstra(int v) {
        if (v < 0 || v >= numOfVertex)
            throw new ArrayIndexOutOfBoundsException();
        boolean[] st = new boolean[numOfVertex];// 默认初始为false
        int[] distance = new int[numOfVertex];// 存放源点到其他点的矩离
        for (int i = 0; i < numOfVertex; i++)
            for (int j = i + 1; j < numOfVertex; j++) {
                if (edges[i][j] == 0) {
                    edges[i][j] = Integer.MAX_VALUE;
                    edges[j][i] = Integer.MAX_VALUE;
                }
            }
        for (int i = 0; i < numOfVertex; i++) {
            distance[i] = edges[v][i];
        }
        st[v] = true;
        // 处理从源点到其余顶点的最短路径
        for (int i = 0; i < numOfVertex; ++i) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            // 比较从源点到其余顶点的路径长度
            for (int j = 0; j < numOfVertex; ++j) {
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
                    if (edges[index][w] != Integer.MAX_VALUE && (min + edges[index][w] < distance[w]))
                        distance[w] = min + edges[index][w];
                }
        }
        return distance;
    }
}
