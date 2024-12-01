package cn.willian.coding.leetcode.graph;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:28:55
 */
public interface IGraph<E> {

    /**
     * 创建一个图
     */
    IGraph<E> createGraph();

    /**
     * 获取顶点的个数
     */
    int getNumOfVertex();

    /**
     * 插入顶点
     */
    boolean insertVertex(E element);

    /**
     * 删除顶点
     */
    boolean deleteVertex(E element);

    /**
     * 定位顶点的位置
     */
    int indexOfVertex(E v);

    /**
     * 定位指定位置的顶点
     */
    E valueOfVertex(int v);

    /**
     * 插入边
     */
    boolean insertEdge(int v1, int v2, int weight);

    /**
     * 删除边
     */
    boolean deleteEdge(int v1, int v2);

    /**
     * 查找边
     */
    int getEdge(int v1, int v2);

    /**
     * 深度优先搜索遍历
     */
    String depthFirstSearch(int v);

    /**
     * 广度优先搜索遍历
     */
    String breadFirstSearch(int v);

    /**
     * 查找源点到其它顶点的路径
     */
    int[] dijkstra(int v);
}
