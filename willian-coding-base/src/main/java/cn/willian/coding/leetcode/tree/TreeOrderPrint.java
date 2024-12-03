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
        System.out.print(root.val + " ");
        preOrderWithRecur(root.left);
        preOrderWithRecur(root.right);
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
            System.out.print(node.val + " ");
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

    /**
     * 中序遍历-递归版本 左 -> 根 -> 右
     */
    public static void inOrderWithRecur(TreeNode root) {

        if (root == null) {
            return;
        }
        inOrderWithRecur(root.left);
        System.out.print(root.val + " ");
        inOrderWithRecur(root.right);
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
                cur = cur.left;
            } else {
                // 弹出时打印
                cur = stack.pop();
                System.out.print(cur.val + " ");
                // 存在右节点时，将右节点的左子树也全部放入栈中
                cur = cur.right;
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
        postOrderWithRecur(root.left);
        postOrderWithRecur(root.right);
        System.out.print(root.val + " ");
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
            if (node.left != null) {
                stack.push(node.left);
            }
            // 再压右节点
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!result.isEmpty()) {
            TreeNode node = result.pop();
            System.out.print(node.val + " ");
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
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
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
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    levelElements.add(node.val);
                }
            }
            result.add(levelElements);
        }
        return result;
    }

    /**
     * morris遍历
     */
    public static void morris(TreeNode root) {

        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            // 有左子节点才进入
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 说明第一次来到该节点,mostRight 变成左子树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 将mostRight的右节点回复
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    /**
     * morris遍历-先序遍历
     */
    public static void morrisPre(TreeNode root) {

        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            // 有左子节点才进入
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 说明第一次来到该节点,mostRight 变成左子树上的最右节点
                if (mostRight.right == null) {
                    System.out.println(cur.val);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 将mostRight的右节点回复
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.val);
            }
            cur = cur.right;
        }
    }

    /**
     * morris遍历-中序遍历
     */
    public static void morrisIn(TreeNode root) {

        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            // 有左子节点才进入
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 说明第一次来到该节点,mostRight 变成左子树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 将mostRight的右节点回复
                    mostRight.right = null;
                }
            }
            System.out.println(cur.val);
            cur = cur.right;
        }
    }

    /**
     * morris遍历-后续序遍历
     */
    public static void morrisPost(TreeNode root) {

        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            // 有左子节点才进入
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 说明第一次来到该节点,mostRight 变成左子树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 将mostRight的右节点回复
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(cur);
    }

    // 逆序打印
    private static void printEdge(TreeNode node) {

        TreeNode tail =  reverseEdge(node);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private static TreeNode reverseEdge(TreeNode node) {

        TreeNode prev = null;
        TreeNode next = null;
        while (node != null) {
            // 保留下一个节点
            next = node.right;
            // 下一个节点指向临时定义的上一个节点
            node.right = prev;
            // 上一个节点往下一个位置移动
            prev = node;
            // 当前节点往下一个位置移动
            node = next;
        }
        return prev;
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
            if (node.left != null) {
                levelMap.put(node.left, currentNodeLevel + 1);
                queue.offer(node.left);
            }
            if (node.right != null) {
                levelMap.put(node.right, currentNodeLevel + 1);
                queue.offer(node.right);
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
        int leftMaxDepth = getMaxDepth(root.left) + 1;
        // 获取右节点最大深度
        int rightMaxDepth = getMaxDepth(root.right) + 1;
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
        int leftMinDepth = getMinDepth(root.left) + 1;
        // 获取右节点最小深度
        int rightMinDepth = getMinDepth(root.right) + 1;
        return Math.min(leftMinDepth, rightMinDepth);
    }
}
