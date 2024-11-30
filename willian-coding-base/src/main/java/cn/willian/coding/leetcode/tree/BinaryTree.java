package cn.willian.coding.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 16:44:18
 */
public class BinaryTree {

    public static void main(String[] args) {

        TreeNode treeNode = TreeNode.init();
        boolean isSearchBinaryTree = isSearchBinaryTree2(treeNode);
        System.out.println("是否为搜索二叉树: " + isSearchBinaryTree);

        boolean isCompleteBinaryTree = isCompleteBinaryTree(treeNode);
        System.out.println("是否为完全二叉树: " + isCompleteBinaryTree);

        boolean isFullBinaryTree = isFullBinaryTree(treeNode);
        System.out.println("是否为满二叉树: " + isFullBinaryTree);
    }

    /**
     * 是否为搜索二叉树 采用中序方式 需要满足条件如下
     * <p>
     * 左节点数 < 右节点数
     */
    public static boolean isSearchBinaryTree(TreeNode root) {

        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int preValue = Integer.MIN_VALUE;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                // 当前左节点不为空，持续放入队列
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.getValue() < preValue) {
                    return false;
                } else {
                    preValue = cur.getValue();
                }
                cur = cur.right;
            }
        }
        return true;
    }

    /**
     * 是否为搜索二叉树 采用中序方式 需要满足条件如下
     * <p>
     * 左节点数 < 右节点数
     */
    public static boolean isSearchBinaryTree2(TreeNode root) {

        if (root == null) {
            return false;
        }
        return getSearchBinaryTree(root).isSearch;
    }

    public static SearchTreeInfo getSearchBinaryTree(TreeNode node) {

        if (node == null) {
            return null;
        }
        // 获取左子树的信息
        SearchTreeInfo leftData = getSearchBinaryTree(node.getLeft());
        SearchTreeInfo rightData = getSearchBinaryTree(node.getRight());

        int minValue = node.getValue();
        int maxValue = node.getValue();
        if (leftData != null) {
            minValue = Math.min(leftData.minValue, minValue);
            maxValue = Math.max(leftData.maxValue, maxValue);
        }
        if (rightData != null) {
            minValue = Math.min(rightData.minValue, minValue);
            maxValue = Math.max(rightData.maxValue, maxValue);
        }
        boolean isSearch = true;
        if (leftData != null &&
        // 左子树非二叉搜索树 或 左节点的最大值 已经大于等于 当前值
            (!leftData.isSearch || leftData.maxValue >= node.getValue())) {
            isSearch = false;
        }
        if (rightData != null &&
        // 右子树非二叉搜索树 或 右节点的最小值 已经小于等于 当前值
            (!rightData.isSearch || rightData.minValue <= node.getValue())) {
            isSearch = false;
        }
        return new SearchTreeInfo(isSearch, minValue, maxValue);
    }

    /**
     * 是否为完全二叉树 使用宽度优先遍历 需要满足条件如下
     * <p>
     * 1)、任意节点，有右节点，无左节点，返回false
     * <p>
     * 2)、在1条件不违规的情况下，如果遇到了第一个左右不全的节点，后续节点一定都得是叶子节点
     */
    public static boolean isCompleteBinaryTree(TreeNode root) {

        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode left;
        TreeNode right;
        boolean isFirstLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            left = node.getLeft();
            right = node.getRight();
            if (
            // 在第一次左右节点不全之后，后续左右必须为空
            (isFirstLeaf && (left != null || right != null) ||
            // 有右节点，无左节点
                (left == null && right != null))) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            // 确定第一次左右节点不双全
            if (left == null || right == null) {
                isFirstLeaf = true;
            }
        }
        return true;
    }

    /**
     * 是否为满二叉树 需要满足条件如下：
     * <p>
     * N(节点数量) = 2^L(树的深度) -1
     */
    public static boolean isFullBinaryTree(TreeNode root) {

        if (root == null) {
            return true;
        }
        FullTreeInfo fullTree = getFullBinaryTree(root);
        return fullTree.getNodeNumbers() == (1 << fullTree.getHeight() - 1);
    }

    private static FullTreeInfo getFullBinaryTree(TreeNode root) {

        if (root == null) {
            return new FullTreeInfo(0, 0);
        }
        FullTreeInfo leftData = getFullBinaryTree(root.getLeft());
        FullTreeInfo rightData = getFullBinaryTree(root.getRight());

        int height = Math.max(leftData.getHeight(), rightData.getHeight()) + 1;
        int nodes = leftData.getNodeNumbers() + rightData.getNodeNumbers() + 1;

        return new FullTreeInfo(nodes, height);
    }

    /**
     * 是否为平衡二叉树 需要满足条件如下：
     * <p>
     * 1)、左子树为平衡二叉树
     * <p>
     * 2)、右子树为平衡二叉树
     * <p>
     * 3)、|左高 - 右高| <= 1
     */
    public static boolean isBalanceBinaryTree(TreeNode root) {

        return getBalanceTree(root).isBalance();
    }

    // 从左树获取信息
    // 从右树获取信息
    public static BalanceTreeInfo getBalanceTree(TreeNode node) {

        if (node == null) {
            return new BalanceTreeInfo(true, 0);
        }
        // 左节点数据信息
        BalanceTreeInfo leftData = getBalanceTree(node.left);
        // 右节点数据信息
        BalanceTreeInfo rightData = getBalanceTree(node.right);

        // 获取当前树的高度
        int height = Math.max(leftData.getHeight(), rightData.getHeight()) + 1;
        // 判断是否是平衡树
        boolean isBalance =
            // 左树是平衡树
            leftData.isBalance
                // 右树是平衡树
                && rightData.isBalance
                // 左右高度差小于等于1
                && Math.abs(leftData.height - rightData.height) <= 1;
        return new BalanceTreeInfo(isBalance, height);
    }

    @Data
    public static class BalanceTreeInfo {

        /**
         * 是否是平衡
         */
        private boolean isBalance;
        /**
         * 树的高度
         */
        private Integer height;

        public BalanceTreeInfo(boolean isBalance, Integer height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    @Data
    public static class FullTreeInfo {

        /**
         * 节点个数
         */
        private Integer nodeNumbers;
        /**
         * 树的高度
         */
        private Integer height;

        public FullTreeInfo(Integer nodeNumbers, Integer height) {
            this.nodeNumbers = nodeNumbers;
            this.height = height;
        }
    }

    @Data
    public static class SearchTreeInfo {

        /**
         * 是否是搜索二叉树
         */
        private boolean isSearch;
        /**
         * 树的最小值
         */
        private Integer minValue;
        /**
         * 树的最大值
         */
        private Integer maxValue;

        public SearchTreeInfo(boolean isSearch, Integer minValue, Integer maxValue) {
            this.isSearch = isSearch;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
}
