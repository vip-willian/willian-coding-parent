package cn.willian.coding.leetcode.array;

/**
 * 解法思想：滑动窗口
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-05 10:17:24
 */
// https://leetcode.cn/problems/minimum-size-subarray-sum/description/
// 209. 长度最小的子数组
// 给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其总和大于等于 target 的长度最小的子数组
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
public class MinLengthChildArray {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minChildLength(nums, 7));
    }

    public static int minChildLength(int[] nums, int targetSum) {

        // 记录滑动窗口内元素和
        int sum = 0;
        // 记录滑动窗口的大小
        int subLength = 0;
        // 记录最终最小的和
        int result = Integer.MAX_VALUE;
        // 有一个变量记录滑动窗口的开始位置
        int i = 0;

        // j 代表滑动窗口的截止位置
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= targetSum) {
                // 获取子序列的长度
                subLength = (j - i) + 1;
                // 获取最小的长度
                result = Math.min(result, subLength);
                // 该窗口已经不满足了，需要移动到下一个窗口
                // 需要做2步，第一步，把第一个窗口的值从总和里去除
                // 第二步，需要将i的位置后移
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
