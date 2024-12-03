package cn.willian.coding.leetcode.dp;

import java.util.Arrays;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 20:18:21
 */
// 322. 零钱兑换
// https://leetcode.cn/problems/coin-change/description
public class Code322_CoinChange {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {

        // 从小往大的金额去推
        // 遍历全部硬币数组，去获取最小使用的零钱数
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int rest = 1; rest <= amount; rest++) {
            // 最小硬币数量 等于 f(1,10)、f(2,9),f(5,6) 的 最小值 + 当前自身已选择了一枚硬币
            for (int coin : coins) {
                if (coin <= rest) {
                    // 之前找到最少的硬币数 和 现在选择的一个硬币数 取最小值
                    dp[rest] = Math.min(dp[rest], dp[rest - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
