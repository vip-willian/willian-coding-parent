package cn.willian.coding.leetcode.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 23:49:43
 */
public class IPO {

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
