package cn.willian.coding.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import cn.willian.coding.leetcode.tree.TreeNode;

/**
 * 深度优先遍历DFS -> Depth First Search
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 20:56:59
 */
public class TreeDfs {

    private static final List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {

        TreeNode tree = TreeNode.init();
        System.out.println("深度优先遍历 --- 递归版本");
        dfsWithRecursion(tree);
        System.out.println("深度优先遍历 --- 栈版本");
        dfsWithStack(tree);
        System.out.println("深度优先遍历 --- 记录每一层的结果");
        List<List<Integer>> result = dfsWithRecordLevel(tree);
        System.out.println(result);
    }

    // 深度优先遍历，递归版本
    public static void dfsWithRecursion(TreeNode root) {

        if (Objects.isNull(root)) {
            return;
        }
        System.out.println("当前节点：" + root.val);
        // 遍历左边
        dfsWithRecursion(root.left);
        // 遍历右边
        dfsWithRecursion(root.right);
    }

    // 深度优先遍历，栈版本
    public static void dfsWithStack(TreeNode root) {

        if (Objects.isNull(root)) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            System.out.println("当前节点：" + node.val);

            // 先压右节点
            if (node.right != null) {
                stack.push(node.right);
            }
            // 再压左节点
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 深度优先遍历，记录每一层的结果
    public static List<List<Integer>> dfsWithRecordLevel(TreeNode root) {

        dfsWithLevel(root, 0);
        return result;
    }

    private static void dfsWithLevel(TreeNode node, int level) {

        if (Objects.isNull(node)) {
            return;
        }

        if (result.size() < level + 1) {
            result.add(new ArrayList<>());
        }

        List<Integer> levelList = result.get(level);
        levelList.add(node.val);

        // 遍历左边
        dfsWithLevel(node.left, level + 1);
        // 遍历右边
        dfsWithLevel(node.right, level + 1);
    }
}
