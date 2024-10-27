package cn.willian.coding.leetcode;

/**
 * 740. 删除并获得点数
 * https://leetcode.cn/problems/delete-and-earn/description/?envType=study-plan-v2&envId=dynamic-programming
 * <p>
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,2]
 * <p>
 * 输出：6
 * <p>
 * 解释： 删除 4 获得 4 个点数，因此 3 也被删除。 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,3,3,3,4]
 * <p>
 * 输出：9
 * <p>
 * 解释： 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。 总共获得 9 个点数。
 * <p>
 *
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104
 * <p>
 * 1 <= nums[i] <= 104
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 17:19:15
 */
public class DeleteAndGetPoints {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        System.out.println(deleteAndEarn1(nums));
        System.out.println(deleteAndEarn2(nums));
    }

    public static int deleteAndEarn1(int[] nums) {

        // 将原来的数组转为词频统计
        int maxNum = getMaxNum(nums);
        int[] counts = getCountsNum(nums, maxNum);
        return loop(maxNum, counts);
    }

    public static int deleteAndEarn2(int[] nums) {

        // 将原来的数组转为词频统计
        int maxNum = getMaxNum(nums);
        int[] countNums = getCountsNum(nums, maxNum);

        int[] dp = new int[maxNum + 1];
        // 1有多少个数
        dp[1] = countNums[1];
        for (int data = 2; data <= maxNum; data++) {
            // 要删除这个节点数据，获取这个位置上能得到的点数 + 下下个位置能获取的点数
            int p1 = countNums[data] * data + dp[data - 2];
            // 不要删除这个这个节点的数据，获取从下一个位置删除后获取的最大点数
            int p2 = dp[data - 1];
            dp[data] = Math.max(p1, p2);
        }
        return dp[maxNum];
    }

    private static int[] getCountsNum(int[] nums, int maxNum) {

        int[] counts = new int[maxNum + 1];
        for (int num : nums) {
            counts[num]++;
        }
        return counts;
    }

    // 从最大的数据开始删除后，后续能获取的最大点数
    public static int loop(int data, int[] countNums) {

        if (data == 0) {
            return 0;
        }
        if (data == 1) {
            return countNums[data];
        }
        // 要删除这个节点数据，获取这个位置上能得到的点数 + 下下个位置能获取的点数
        int p1 = countNums[data] * data + loop(data - 2, countNums);
        // 不要删除这个这个节点的数据，获取从下一个位置删除后获取的最大点数
        int p2 = loop(data - 1, countNums);
        return Math.max(p1, p2);
    }

    // 每次优先取最大的数
    public static int getMaxNum(int[] nums) {

        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        return maxNum;
    }
}
