package cn.willian.coding.leetcode.tree;

import java.util.Objects;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 21:31:20
 */

// 给定一个二叉树，找出最大深度和最小深度
public class TreeDepth {

    public static void main(String[] args) {
        TreeNode tree = TreeNode.init();
        int maxDepth = getMaxDepth(tree);
        System.out.println("当前树的最大深度为：" + maxDepth);
        int minDepth = getMinDepth(tree);
        System.out.println("当前树的最小深度为：" + minDepth);
    }

    public static int getMaxDepth(TreeNode node) {

        if (Objects.isNull(node)) {
            return 0;
        }
        // 获取左节点最大深度
        int leftMaxDepth = getMaxDepth(node.getLeft()) + 1;
        // 获取右节点最大深度
        int rightMaxDepth = getMaxDepth(node.getRight()) + 1;
        return Math.max(leftMaxDepth, rightMaxDepth);
    }

    public static int getMinDepth(TreeNode node) {

        if (Objects.isNull(node)) {
            return 0;
        }
        // 获取左节点最小深度
        int leftMinDepth = getMinDepth(node.getLeft()) + 1;
        // 获取右节点最小深度
        int rightMinDepth = getMinDepth(node.getRight()) + 1;
        return Math.min(leftMinDepth, rightMinDepth);
    }
}
