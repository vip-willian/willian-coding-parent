package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 21:35:25
 */
// 300. 最长递增子序列
// https://leetcode.cn/problems/longest-increasing-subsequence/description
public class Code300_longestIncreasingSubSequence {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        // 数组元素只有1个，那么递增子序列只有1
        dp[0] = 1;
        int maxlength = 1;
        // 从左到右开始每个位置获取最大递增子序列长度
        for (int i = 1; i < n; i++) {
            // 该位置至少有1个递增子序列
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 只有i元素位置比j元素位置大，才说明是递增，才有统计的意义
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxlength = Math.max(maxlength, dp[i]);
        }
        return maxlength;
    }
}
