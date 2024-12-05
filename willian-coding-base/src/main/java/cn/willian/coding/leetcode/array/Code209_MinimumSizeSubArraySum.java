package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 10:57:02
 */
// https://leetcode.cn/problems/minimum-size-subarray-sum/description
// 209. 长度最小的子数组
// 给定一个含有 n 个正整数的数组和一个正整数 target 。 找出该数组中满足其总和大于等于 target 的长度最小的子数组
// [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

// 示例 1：
// 输入：target = 7, nums = [2,3,1,2,4,3]
// 输出：2
// 解释：子数组 [4,3] 是该条件下的长度最小的子数组。

// 示例 2：
// 输入：target = 4, nums = [1,4,4]
// 输出：1

// 示例 3：
// 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
// 输出：0
public class Code209_MinimumSizeSubArraySum {

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(target, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {

        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
        int n = nums.length;
        // 定义滑动窗口的开始指针
        int start = 0;
        // 定义滑动窗口的结束指针
        int end = 0;
        // 定义最少子数组的长度
        int minSubArrayLen = Integer.MAX_VALUE;
        // 定义子数组的总和
        int sum = 0;
        while (end < n) {
            // 统计滑动窗口内元素的总和
            sum += nums[end];
            // 循环判断元素内的总和是否大于等于target
            while (sum >= target) {
                // 满足条件，更新最小子序列的长度
                minSubArrayLen = Math.min(minSubArrayLen, (end - start) + 1);
                // 总和要减去start位置上的数
                sum -= nums[start];
                // 开始窗口指针后移，向前滑动
                start++;
            }
            end++;
        }
        return minSubArrayLen == Integer.MAX_VALUE ? 0 : minSubArrayLen;
    }
}
