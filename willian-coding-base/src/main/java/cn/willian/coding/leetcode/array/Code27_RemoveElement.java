package cn.willian.coding.leetcode.array;

import cn.willian.coding.tools.Prints;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 09:49:21
 */
// 27. 移除元素
// https://leetcode.cn/problems/remove-element/description
public class Code27_RemoveElement {

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int count = removeElement1(nums, val);
        System.out.println(count);
        Prints.printArray(nums);
    }

    // 双指针法 left right 指针
    // left指向的是待插入元素的位置
    // right指向的是要处理的元素位置
    public static int removeElement1(int[] nums, int val) {

        int left = 0;
        // 不等于的放前面
        for (int right = 0; right < nums.length; right++) {
            // 如果元素值 不等于 目标值
            if (nums[right] != val) {
                // left指针位置的值等于right的值
                nums[left] = nums[right];
                // 该位置已经插入过一个元素，left指针后移一位
                left++;
            }
        }
        return left;
    }

    // 双指针法
    // left代表左边第一个元素
    // right代表右边第一个元素
    public static int removeElement2(int[] nums, int val) {

        int left = 0;
        int right = nums.length;
        while (left < right) {
            // 如果左边的元素 等于 目标值
            if (nums[left] == val) {
                // 将右边元素取代左边
                nums[left] = nums[right - 1];
                right--;
            } else {
                // 左边不等于 目标值 去下个位置再判断
                left++;
            }
        }
        return left;
    }
}
