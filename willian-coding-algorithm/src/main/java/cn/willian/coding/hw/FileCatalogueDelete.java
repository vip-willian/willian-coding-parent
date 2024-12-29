package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 16:13:04
 */
// 序号12
// 题目：目录删除
// 某个文件系统由N个目录，每个目录都有一个独一无二的ID，每个目录只有一个父目录，但每个父目录下可
// 以由0个或多个子目录，目录结构呈树状结构，假设，根目录的ID为0，且根目录没有父目录，其他所有目录的
// ID用唯一的正整数表示，并统一编号。
// 现给定目录ID和其父目录ID对应的父子关系表[子目录ID，父目录ID]，以及一个待删除的目录ID，请计算
// 并返回一个ID序列，表示因为删除制定目录ID后剩下的所有目录，返回的ID序列以递增顺序输出。
//
// 注意：
// 1、被删除的目录旱或文件编号一定在输入的ID序列中
// 2、当一个目录删除时，它所有的子目录都会被删除
//
// 示例：
// 输入：
// 5
// 8 6
// 10 8
// 6 0
// 20 8
// 2 6
// 8
// 输出：
// 2 6
public class FileCatalogueDelete {
    public static void main(String[] args) {

        // 构建一个树
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();

        // 第一步： 构建一颗树
        // 用Hash表存储父节点到子节点的映射
        Map<Integer, List<Integer>> tree = new HashMap<>();
        // 存储所有子节点的ID
        Set<Integer> allIds = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int childId = sc.nextInt();
            int parentId = sc.nextInt();
            tree.computeIfAbsent(parentId, k -> new ArrayList<>()).add(childId);
            allIds.add(childId);
        }

        // 第二步：标记要删除的节点 BFS优先遍历
        int deleteId = sc.nextInt();
        Set<Integer> todoDeletedIds = new HashSet<>();
        todoDeletedIds.add(deleteId);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(deleteId);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            // 说明要删除的目录是一个父节点
            if (tree.containsKey(current)) {
                // 获取到它全部的子节点
                List<Integer> children = tree.get(current);
                for (Integer child : children) {
                    // 放入到删除的队列中
                    if (!todoDeletedIds.contains(child)) {
                        todoDeletedIds.add(child);
                        // 可能待删除的节点也属于一个父节点，需要加入到队列中进行再次判断
                        queue.offer(child);
                    }
                }
            }
        }
        // 第三步：收集剩余的节点
        List<Integer> remaining = new ArrayList<>();
        for (Integer id : allIds) {
            if (!todoDeletedIds.contains(id)) {
                remaining.add(id);
            }
        }
        // 排序
        remaining.sort(Integer::compareTo);
        // 打印
        for (int i = 0; i < remaining.size(); i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            System.out.print(remaining.get(i));
        }
        System.out.println();
    }
}
