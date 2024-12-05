package cn.willian.coding.leetcode.unionfind;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 14:04:06
 */
public class UnionFindTest {

    public static void main(String[] args) {

        UnionFind unionFind = new UnionFind(10);
        unionFind.print();

        unionFind.union(5, 6);

        unionFind.print();

        unionFind.union(1, 2);
        unionFind.union(2, 3);

        unionFind.print();

        System.out.println(unionFind.isSameSet(5, 2));
    }
}
