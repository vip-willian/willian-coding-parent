package cn.willian.coding.leetcode.unionfind;

import cn.willian.coding.tools.Prints;

/**
 * 并查集
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 13:29:21
 */
public class UnionFind {

    /**
     * 一共有多少个子集合
     */
    private int count;
    /**
     * 记录每个小集合元素个数
     */
    private final int[] size;
    /**
     * 指向父节点的索引位置
     */
    private final int[] father;

    public UnionFind(int n) {

        this.size = new int[n];
        this.father = new int[n];
        this.count = n;
        // 初始化时，默认父元素指向本身
        for (int i = 0; i < n; i++) {
            this.father[i] = i;
            this.size[i] = 1;
        }
    }

    /**
     * 查看元素所属于哪个集合
     *
     * @param e 要查看的元素
     * @return e元素所在的集合
     */
    public int find(int e) {

        // int size = 0;
        // // 节点的父索引不是指向自己，说明不是代表元素
        // while (father[e] != e) {
        // // father[e] = father[father[e]];
        // // 收集沿途中的元素
        // stack[size++] = e;
        // e = father[e];
        // }
        // // 扁平化/路径压缩,减少下次搜索的时长，指向共同的父节点
        // while (size > 0) {
        // father[stack[--size]] = e;
        // }
        // return e;
        // 扁平化/路径压缩,减少下次搜索的时长，指向共同的父节点
        if (e != father[e]) {
            father[e] = find(father[e]);
        }
        return father[e];
    }

    /**
     * 判断两个元素是否同属于一个集合
     *
     * @param x 第一个元素
     * @param y 第二个元素
     * @return 是否同属于一个集合
     */
    public boolean isSameSet(int x, int y) {

        return find(x) == find(y);
    }

    /**
     * 合并两个元素所在的集合，也就是连接两个元素
     *
     * @param x 第一个元素
     * @param y 第二个元素
     */
    public void union(int x, int y) {

        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return;
        }
        int sizeX = size[fx];
        int sizeY = size[fy];
        if (sizeY < sizeX) {
            // 小挂大
            father[fy] = fx;
            size[fx] += sizeY;
        } else {
            father[fx] = fy;
            size[fy] += sizeX;
        }
        count--;
    }

    public void print() {
        Prints.printArray(father);
    }
}
