package cn.willian.coding.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 14:36:11
 */
// https://leetcode.cn/problems/sum-root-to-leaf-numbers/description
// 129. 求根节点到叶节点数字之和
// 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
// 每条从根节点到叶节点的路径都代表一个数字：
// 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
// 计算从根节点到叶节点生成的 所有数字之和 。
// 叶节点 是指没有子节点的节点。

// 示例 1：
// 输入：root = [1,2,3]
// 输出：25
// 解释：
// 从根到叶子节点路径 1->2 代表数字 12
// 从根到叶子节点路径 1->3 代表数字 13
// 因此，数字总和 = 12 + 13 = 25

// 示例 2：
// 输入：root = [4,9,0,5,1]
// 输出：1026
// 解释：
// 从根到叶子节点路径 4->9->5 代表数字 495
// 从根到叶子节点路径 4->9->1 代表数字 491
// 从根到叶子节点路径 4->0 代表数字 40
// 因此，数字总和 = 495 + 491 + 40 = 1026
public class Code129_SumRootToLeafNumbers {

    public static void main(String[] args) {
        TreeNode root = TreeNode.init();
        System.out.println(sumNumbers1(root));
        System.out.println(dfs2(root));
        System.out.println(bfs1(root));
    }

    public static int sumNumbers1(TreeNode root) {

        return dfs(root, 0);
    }

    // dfs 递归
    public static int dfs(TreeNode root, int sum) {

        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    // dfs 栈
    public static int dfs2(TreeNode root) {

        if (root == null) {
            return 0;
        }
        Stack<TreeSum> stack = new Stack<>();
        stack.push(new TreeSum(root, root.val));

        int sum = 0;
        while (!stack.isEmpty()) {
            TreeSum cur = stack.pop();
            TreeNode node = cur.node;
            int s = cur.sum;
            if (node.left == null && node.right == null) {
                sum += s;
            }
            if (node.left != null) {
                stack.push(new TreeSum(node.left, s * 10 + node.left.val));
            }
            if (node.right != null) {
                stack.push(new TreeSum(node.right, s * 10 + node.right.val));
            }
        }
        return sum;
    }

    // bfs 队列
    public static int bfs1(TreeNode root) {

        if (root == null) {
            return 0;
        }
        Queue<TreeSum> queue = new LinkedList<>();
        queue.add(new TreeSum(root, root.val));

        int sum = 0;
        while (!queue.isEmpty()) {
            TreeSum cur = queue.poll();
            TreeNode node = cur.node;
            int s = cur.sum;
            if (node.left == null && node.right == null) {
                sum += s;
            }
            if (node.left != null) {
                queue.add(new TreeSum(node.left, s * 10 + node.left.val));
            }
            if (node.right != null) {
                queue.add(new TreeSum(node.right, s * 10 + node.right.val));
            }
        }
        return sum;
    }

    public static class TreeSum {

        private final TreeNode node;
        private final Integer sum;

        public TreeSum(TreeNode node, Integer sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}
