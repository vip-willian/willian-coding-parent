package cn.willian.coding.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cost = [10,15,20]
 * <p>
 * 输出：15
 * <p>
 * 解释：你将从下标为 1 的台阶开始。 - 支付 15 ，向上爬两个台阶，到达楼梯顶部。 总花费为 15 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * <p>
 * 输出：6
 * <p>
 * 解释：你将从下标为 0 的台阶开始。 - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。 - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。 - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。 - 支付 1
 * ，向上爬一个台阶，到达下标为 7 的台阶。 - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。 - 支付 1 ，向上爬一个台阶，到达楼梯顶部。 总花费为 6 。
 * <p>
 * 提示：
 * <p>
 * 2 <= cost.length <= 1000
 * <p>
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 16:19:46
 */
public class MinFeeStaircase {

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        // int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs1(cost));
        System.out.println(minCostClimbingStairs2(cost));
    }

    public static int minCostClimbingStairs2(int[] cost) {

        int N = cost.length;
        int[] dp = new int[N + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + (i == N ? 0 : cost[i]);
        }
        return dp[N];
    }

    public static int minCostClimbingStairs1(int[] cost) {

        return loop(cost.length, cost);
    }

    // 到达i位置需要花费的最少费用
    public static int loop(int i, int[] cost) {

        if (i < 0) {
            return 0;
        }
        if (i == 0 || i == 1) {
            return cost[i];
        }
        // 走1步，需要的最少费用
        int p1 = loop(i - 1, cost) + (i == cost.length ? 0 : cost[i]);
        // 走2步，需要的最少费用
        int p2 = loop(i - 2, cost) + (i == cost.length ? 0 : cost[i]);
        return Math.min(p1, p2);
    }
}
