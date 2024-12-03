package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 23:18:16
 */
// 121. 买卖股票的最佳时机
// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description
public class Code121_BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int profit = maxProfit(prices);
        System.out.println(profit);
    }

    public static int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            // 在卖出之前获得的最低价
            minPrice = Math.min(minPrice, price);
            // 计算最大的利润
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}
