package cn.willian.coding.leetcode.dp;

import java.util.Arrays;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-06 13:33:02
 */
// https://leetcode.cn/problems/minimum-cost-for-tickets/description/
// 983. 最低票价
// 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
// 火车票有 三种不同的销售方式 ：
// 一张 为期一天 的通行证售价为 costs[0] 美元；
// 一张 为期七天 的通行证售价为 costs[1] 美元；
// 一张 为期三十天 的通行证售价为 costs[2] 美元。
// 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
// 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费 。

// 示例 1：
// 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
// 输出：11
// 解释：
// 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
// 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
// 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
// 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
// 你总共花了 $11，并完成了你计划的每一天旅行。

// 示例 2：
// 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
// 输出：17
// 解释：
// 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
// 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
// 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
// 你总共花了 $17，并完成了你计划的每一天旅行。
public class Code983_MinimumCostForTickets {

    private static final int[] ticketDays = {1, 7, 30};

    public static void main(String[] args) {
        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs = {2, 7, 15};
        System.out.println(minCostTickets1(days, costs));
        System.out.println(minCostTickets2(days, costs));
    }

    public static int minCostTickets1(int[] days, int[] costs) {

        return process(days, costs, 0);
    }

    public static int minCostTickets2(int[] days, int[] costs) {

        int n = days.length;
        int[] dp = new int[366];
        Arrays.fill(dp, 0, n + 1, Integer.MAX_VALUE);
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0, next = i; k < 3; k++) {
                // 选择1天的方案，我的最小花费
                // 选择7天的方案，我的最小花费
                // 选择30天的方案，我的最小花费
                while (next < days.length && days[next] < days[i] + ticketDays[k]) {
                    next++;
                }
                // 花费这些钱数，能够维持到的天数 + 下一次开始又需要花费的钱数
                dp[i] = Math.min(dp[i], costs[k] + dp[next]);
            }
        }
        return dp[0];
    }

    // 从[i天....]开始旅行最小花费
    private static int process(int[] days, int[] costs, int i) {

        // 已经旅行结束了，最小花费为0
        if (i == days.length) {
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        for (int k = 0, next = i; k < 3; k++) {
            // 选择1天的方案，我的最小花费
            // 选择7天的方案，我的最小花费
            // 选择30天的方案，我的最小花费
            while (next < days.length && days[next] < days[i] + ticketDays[k]) {
                next++;
            }
            // 花费这些钱数，能够维持到的天数 + 下一次开始又需要花费的钱数
            minCost = Math.min(minCost, costs[k] + process(days, costs, next));
        }
        return minCost;
    }
}
