package cn.willian.coding.leetcode;

/**
 * 198. 打家劫舍
 * <p>
 * https://leetcode.cn/problems/house-robber/description/?envType=study-plan-v2&envId=dynamic-programming
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * <p>
 * 输出：4
 * <p>
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * <p>
 * 输出：12
 * <p>
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 *
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * <p>
 * 0 <= nums[i] <= 400
 *
 * <p>
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 17:04:25
 */
public class RansackHomesAndLootHouses {
    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob1(nums));
        System.out.println(rob2(nums));
    }

    public static int rob1(int[] nums) {

        return loop(0, nums);
    }

    public static int rob2(int[] nums) {

        int[] dp = new int[nums.length + 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            // 偷这家获得的最大金额
            int p1 = nums[i] + (i + 2 > nums.length ? 0 : dp[i + 2]);
            int p2 = dp[i + 1];
            // 不偷这家获取的最大金额
            dp[i] = Math.max(p1, p2);
        }
        return dp[0];
    }

    // index 代表 偷到第index房屋时，产生的最高金额
    // 返回 最高金额
    public static int loop(int index, int[] nums) {

        // 已经没得偷，返回0
        if (index >= nums.length) {
            return 0;
        }
        // 偷这家获得的金额，偷完这家只能去下下家获取
        int p1 = nums[index] + loop(index + 2, nums);
        // 不偷这家获得的金额，不偷这家，直接从下家获取
        int p2 = loop(index + 1, nums);
        return Math.max(p1, p2);
    }
}
