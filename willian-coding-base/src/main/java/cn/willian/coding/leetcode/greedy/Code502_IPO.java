package cn.willian.coding.leetcode.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 23:49:43
 */
// 502. IPO
// https://leetcode.cn/problems/ipo/description

// 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
// 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
// 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
// 答案保证在 32 位有符号整数范围内。

// 示例 1：
// 输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
// 输出：4
// 解释：
// 由于你的初始资本为 0，你仅可以从 0 号项目开始。
// 在完成后，你将获得 1 的利润，你的总资本将变为 1。
// 此时你可以选择开始 1 号或 2 号项目。
// 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
// 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。

// 示例 2：
// 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
// 输出：6
public class Code502_IPO {

    public static void main(String[] args) {

        int k = 3;
        int w = 0;
        // 项目成本
        int[] capital = {0, 1, 2};
        // 项目利润
        int[] profits = {1, 2, 3};
        System.out.println(findMaximizedCapital(k, w, profits, capital));
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        PriorityQueue<ProjectPool> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<ProjectPool> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < capital.length; i++) {
            minCostQueue.add(new ProjectPool(capital[i], profits[i]));
        }
        for (int i = 0; i < k; i++) {
            // 满足条件的项目进入大根堆，项目池中所需要的花费 小于等于 我当前持有的金额
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost <= w) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            if (maxProfitQueue.isEmpty()) {
                return w;
            }
            // 取出一个利润最大的项目执行，获取项目的利润
            w += maxProfitQueue.peek().profit;
        }
        return w;
    }

    @Data
    public static class ProjectPool {

        private int cost;
        private int profit;

        public ProjectPool(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class MinCostComparator implements Comparator<ProjectPool> {

        @Override
        public int compare(ProjectPool a, ProjectPool b) {
            return a.getCost() - b.getCost();
        }
    }

    public static class MaxProfitComparator implements Comparator<ProjectPool> {

        @Override
        public int compare(ProjectPool a, ProjectPool b) {
            return b.getProfit() - a.getProfit();
        }
    }
}
