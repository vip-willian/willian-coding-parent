package cn.willian.coding.leetcode.tree;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 10:33:33
 */
// https://leetcode.cn/problems/maximum-depth-of-binary-tree/description
public class Code104_MaximumDepthOfBinaryTree {

    /**
     * 二叉树最大深度
     */
    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        // 返回左子树最大深度 或 右子树最大深度 的 最大值 + 1
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
