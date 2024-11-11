package cn.willian.coding.leetcode.array;

import java.util.Arrays;

/**
 * 解法思路: 左右双指针
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-05 09:58:07
 */
// https://leetcode.cn/problems/squares-of-a-sorted-array/description/
// 977. 有序数组的平方
// 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
// 示例 1：
// 输入：nums = [-4,-1,0,3,10]
// 输出：[0,1,9,16,100]
// 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
// 示例 2：
// 输入：nums = [-7,-3,2,3,11]
// 输出：[4,9,9,49,121]
public class SortArraySquare {

    public static void main(String[] args) {
        int[] nums = {-7, -3, 2, 3, 11};
        int[] result = new int[nums.length];
        sortArraySquare(nums, result);

        System.out.println(Arrays.toString(result));
    }

    public static void sortArraySquare(int[] nums, int[] result) {

        // 定义一个双指针i 和 j ，一个从前往后遍历，一个从后往前遍历
        int i = 0;
        int j = nums.length - 1;

        // 定义一个变量k，记录新数组写入的位置
        int k = nums.length - 1;

        while (i <= j) {
            int v1 = nums[i] * nums[i];
            int v2 = nums[j] * nums[j];

            if (v1 >= v2) {
                result[k--] = v1;
                i++;
            } else {
                result[k--] = v2;
                j--;
            }
        }
    }
}
