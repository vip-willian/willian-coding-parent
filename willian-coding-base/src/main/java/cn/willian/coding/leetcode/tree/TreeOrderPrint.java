package cn.willian.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 14:39:21
 */
public class TreeOrderPrint {

    public static void main(String[] args) {
        TreeNode root = TreeNode.init();
        System.out.println("---------------------------------");
        System.out.print("递归版本-先序遍历结果: ");
        preOrderWithRecur(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("  栈版本-先序遍历结果: ");
        preOrderWithStack(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("递归版本-中序遍历结果: ");
        inOrderWithRecur(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("  栈版本-中序遍历结果: ");
        inOrderWithStack(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("递归版本-后序遍历结果: ");
        postOrderWithRecur(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("  栈版本-后序遍历结果: ");
        postOrderWithStack(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("队列版本-宽度遍历结果: ");
        breadthOrderWithQueue(root);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("队列版本-获取每层结果: ");
        List<List<Integer>> result = breadthWithQueueOfRecordLevelResult(root);
        System.out.print(result);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("树的最大宽度: ");
        int maxBreadth = getMaxBreadth(root);
        System.out.print(maxBreadth);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("树的最大深度: ");
        int maxDepth = getMaxDepth(root);
        System.out.print(maxDepth);
        System.out.println();
        System.out.println("---------------------------------");
        System.out.print("树的最小深度: ");
        int minDepth = getMinDepth(root);
        System.out.print(minDepth);
        System.out.println();
        System.out.println("---------------------------------");
    }

    /**
     * 先序遍历-递归版本 根 -> 左 -> 右
     */
    public static void preOrderWithRecur(TreeNode root) {

        if (root == null) {
            return;
        }
        System.out.print(root.getValue() + " ");
        preOrderWithRecur(root.getLeft());
        preOrderWithRecur(root.getRight());
    }

    /**
     * 先序遍历-栈版本 根 -> 左 -> 右
     */
    public static void preOrderWithStack(TreeNode root) {

        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.getValue() + " ");
            // 先压右节点
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            // 再压左节点
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    /**
     * 中序遍历-递归版本 左 -> 根 -> 右
     */
    public static void inOrderWithRecur(TreeNode root) {

        if (root == null) {
            return;
        }
        inOrderWithRecur(root.getLeft());
        System.out.print(root.getValue() + " ");
        inOrderWithRecur(root.getRight());
    }

    /**
     * 中序遍历-栈版本 左 -> 根 -> 右
     */
    public static void inOrderWithStack(TreeNode root) {

        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            // 将左子树一起放入栈
            if (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            } else {
                // 弹出时打印
                cur = stack.pop();
                System.out.print(cur.getValue() + " ");
                // 存在右节点时，将右节点的左子树也全部放入栈中
                cur = cur.getRight();
            }
        }
    }

    /**
     * 后序遍历-递归版本 左 -> 右 -> 根
     */
    public static void postOrderWithRecur(TreeNode root) {

        if (root == null) {
            return;
        }
        postOrderWithRecur(root.getLeft());
        postOrderWithRecur(root.getRight());
        System.out.print(root.getValue() + " ");
    }

    /**
     * 后序遍历-栈版本 根 -> 左 -> 右
     */
    public static void postOrderWithStack(TreeNode root) {

        if (root == null) {
            return;
        }
        Stack<TreeNode> result = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.push(node);
            // 先压左节点
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
            // 再压右节点
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
        }
        while (!result.isEmpty()) {
            TreeNode node = result.pop();
            System.out.print(node.getValue() + " ");
        }
    }

    /**
     * 宽度遍历-队列
     */
    public static void breadthOrderWithQueue(TreeNode root) {

        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.getValue() + " ");
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }

    /**
     * 宽度遍历-记录结果
     */
    public static List<List<Integer>> breadthWithQueueOfRecordLevelResult(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> levelElements = new ArrayList<>();
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (Objects.nonNull(node)) {
                    if (node.getLeft() != null) {
                        queue.offer(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        queue.offer(node.getRight());
                    }
                    levelElements.add(node.getValue());
                }
            }
            result.add(levelElements);
        }
        return result;
    }

    /**
     * 获取树最大宽度
     */
    public static int getMaxBreadth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        // 这个节点所属的层级
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelMap.put(root, 1);

        int maxBreadth = Integer.MIN_VALUE;
        // 当前层级变量
        int currentLevel = 1;
        // 当前层级节点总数量
        int currentLevelNodeNumbers = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer currentNodeLevel = levelMap.get(node);
            if (currentLevel == currentNodeLevel) {
                currentLevelNodeNumbers++;
            } else {
                maxBreadth = Math.max(maxBreadth, currentLevelNodeNumbers);
                currentLevel++;
                currentLevelNodeNumbers = 1;
            }
            if (node.getLeft() != null) {
                levelMap.put(node.getLeft(), currentNodeLevel + 1);
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                levelMap.put(node.getRight(), currentNodeLevel + 1);
                queue.offer(node.getRight());
            }
        }
        return maxBreadth;
    }

    /**
     * 获取树最大深度
     */
    public static int getMaxDepth(TreeNode root) {

        if (Objects.isNull(root)) {
            return 0;
        }
        // 获取左节点最大深度
        int leftMaxDepth = getMaxDepth(root.getLeft()) + 1;
        // 获取右节点最大深度
        int rightMaxDepth = getMaxDepth(root.getRight()) + 1;
        return Math.max(leftMaxDepth, rightMaxDepth);
    }

    /**
     * 获取树最大深度
     */
    public static int getMinDepth(TreeNode root) {

        if (Objects.isNull(root)) {
            return 0;
        }
        // 获取左节点最小深度
        int leftMinDepth = getMinDepth(root.getLeft()) + 1;
        // 获取右节点最小深度
        int rightMinDepth = getMinDepth(root.getRight()) + 1;
        return Math.min(leftMinDepth, rightMinDepth);
    }
}
