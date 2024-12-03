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

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            // 最小硬币数量 等于 f(1,10)、f(2,9),f(5,6) 的 最小值 + 当前自身已选择了一枚硬币
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
