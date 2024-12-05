package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 21:53:38
 */
// https://leetcode.cn/problems/first-missing-positive/
// 41. 缺失的第一个正数
// 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
// 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

// 示例 1：
// 输入：nums = [1,2,0]
// 输出：3
// 解释：范围 [1,2] 中的数字都在数组中。

// 示例 2：
// 输入：nums = [3,4,-1,1]
// 输出：2
// 解释：1 在数组中，但 2 没有。

// 示例 3：
// 输入：nums = [7,8,9,11,12]
// 输出：1
// 解释：最小的正数 1 没有出现。
public class Code41_FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums = {-3, 2, 1, 8, 5, 4, 2, 3, 5, 13};
        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {

        // left左边做到i位置上放了i+1的区域
        int left = 0;
        // right...为垃圾数据区域
        // 有垃圾数据存在的情况下，满足不了最好1-r的情况
        int right = nums.length;
        while (left < right) {
            int value = nums[left];
            // left位置上放了left+1
            if (value == left + 1) {
                left++;
            } else if (
            // left位置 小于 left代表垃圾数据 属于垃圾数据
            // left位置 大于 能收集的最大数据 属于垃圾数据
            // left位置 和 left该放的位置上的数据相同，说明有重复数据，也是垃圾数据
            value <= left || value > right || value == nums[value - 1]) {
                swap(nums, left, --right);
            } else {
                // left位置的数 和 left该去位置的数 交换
                swap(nums, left, value - 1);
            }
        }
        return left + 1;
    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
