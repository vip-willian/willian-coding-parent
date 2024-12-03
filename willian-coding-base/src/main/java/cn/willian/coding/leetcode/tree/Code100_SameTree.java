package cn.willian.coding.leetcode.tree;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 10:36:50
 */
// https://leetcode.cn/problems/same-tree/description
public class Code100_SameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        // 左节点是否相同
        boolean leftSame = isSameTree(p.left, q.left);
        // 右节点是否相同
        boolean rightSame = isSameTree(p.right, q.right);
        return leftSame & rightSame && (p.val == q.val);
    }
}
