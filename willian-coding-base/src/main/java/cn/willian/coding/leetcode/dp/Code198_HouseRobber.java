package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 13:31:44
 */
// 198. 打家劫舍
// https://leetcode.cn/problems/house-robber/description
public class Code198_HouseRobber {

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob1(nums));
        System.out.println(rob2(nums));
    }

    public static int rob1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, nums.length - 1);
    }

    public static int rob2(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 从2开始遍历
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    // index 代表剩余可拿房间的数量
    private static int process(int[] nums, int index) {

        // 只剩1间房能获取到的最大金额
        if (index == 0) {
            return nums[index];
        }
        // 只剩2间房能获取到的最大金额
        if (index == 1) {
            return Math.max(nums[index], nums[index - 1]);
        }
        // 其他剩余多建房的时候
        // 可以选择拿index房间的钱 ，剩余房间数量就要减2
        int m1 = nums[index] + process(nums, index - 2);
        // 可以选择不拿index房间的钱，剩余房间数量就要减1
        int m2 = process(nums, index - 1);
        return Math.max(m1, m2);
    }
}
