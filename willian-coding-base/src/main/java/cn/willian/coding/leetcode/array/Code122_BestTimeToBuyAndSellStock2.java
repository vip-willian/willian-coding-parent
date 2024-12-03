package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 23:18:16
 */
// 122. 买卖股票的最佳时机 II
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description
public class Code122_BestTimeToBuyAndSellStock2 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int profit = maxProfit(prices);
        System.out.println(profit);
    }

    public static int maxProfit(int[] prices) {

        int n = prices.length;
        // 0列 在当天没有持有股票的最大利润
        // 1列 在当天持有股票的最大利润
        int[][] dp = new int[n][2];
        // 在第1天没有持有股票的最大利润0
        dp[0][0] = 0;
        // 在第1天持有股票的最大利润-7，说明当天仅仅买入了，才会持有
        dp[0][1] = -prices[0];

        // 从第i天开始继续判断
        for (int i = 1; i < n; i++) {
            // 第i天没有持有股票的最大利润
            // 第i天没有持有股票有2个原因：
            // 1）、前一天没有持有 获得的利润
            // 2）、前一天持有，今天卖出了 获得的利润
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天持有股票的最大利润
            // 第i天持有股票有2个原因：
            // 1）、前一天已经持有 获得的利润
            // 2）、前一天没有持有，今天买入了 获得的利润
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 返回最后一天时，没有持有的最大利润
        return dp[n - 1][0];
    }
}
