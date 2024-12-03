package cn.willian.coding.leetcode.array;

import cn.willian.coding.tools.Prints;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 10:10:34
 */
// 26. 删除有序数组中的重复项
// https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description
public class Code26_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = removeDuplicates(nums);
        System.out.println(k);
        Prints.printArray(nums);
    }

    // 快慢指针解法
    // 慢指针指向要插入的位置 初始位置为1
    // 快指针进行遍历的位置 初始值为1
    public static int removeDuplicates(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1;
        int slow = 1;

        // 判断快指针是否遍历完
        while (fast < n) {
            // 如果当前值 不等于 它前一个元素值
            // 说明这两个相邻元素不相等，慢指针的位置可以赋值
            if (nums[fast] != nums[slow - 1]) {
                nums[slow] = nums[fast];
                // 将位置往下移动到下一个可插入的位置
                ++slow;
            }
            // 快指针继续向下移动进行判断
            ++fast;
        }
        return slow;
    }
}
