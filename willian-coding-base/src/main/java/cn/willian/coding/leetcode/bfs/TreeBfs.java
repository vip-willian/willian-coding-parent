package cn.willian.coding.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import com.google.common.collect.Lists;

import cn.willian.coding.leetcode.tree.TreeNode;

/**
 * 广度优先遍历BFS -> Breath First Search
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-29 20:56:59
 */
public class TreeBfs {

    public static void main(String[] args) {

        TreeNode tree = TreeNode.init();
        System.out.println("广度优先遍历 --- 队列版本");
        bfsWithQueue(tree);
        System.out.println("广度优先遍历 --- 记录每一层的结果");
        List<List<Integer>> result = bfsWithRecordLevel(tree);
        System.out.println(result);

    }

    // 广度优先遍历，队列版本
    public static void bfsWithQueue(TreeNode root) {

        if (Objects.isNull(root)) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println("当前节点：" + node.getValue());
            // 左节点
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            // 右节点
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    // 广度优先遍历，队列版本,记录每一层的结果
    public static List<List<Integer>> bfsWithRecordLevel(TreeNode root) {

        if (Objects.isNull(root)) {
            return Lists.newArrayList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 此时队列元素个数为该层级元素个数
            int levelNumber = queue.size();
            List<Integer> levelElements = new ArrayList<>();
            for (int i = 0; i < levelNumber; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    // 左节点
                    if (node.getLeft() != null) {
                        queue.add(node.getLeft());
                    }
                    // 右节点
                    if (node.getRight() != null) {
                        queue.add(node.getRight());
                    }
                    levelElements.add(node.getValue());
                }
            }
            result.add(levelElements);
        }
        return result;
    }
}
