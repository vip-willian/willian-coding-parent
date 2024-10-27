package cn.willian.coding.leetcode;

import cn.willian.coding.tools.ArrayPrint;

/**
 * 26. 删除有序数组中的重复项
 * <p>
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 * <p>
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * <p>
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过： 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums
 * 的大小不重要。
 * <p>
 * 返回 k 。
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-17 09:35:00
 */
public class RemoveDuplicates1 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = removeDuplicates(nums);
        System.out.println(k);
        ArrayPrint.print(nums);
    }

    // 想象成其实有2个数组
    // oldIndex 老数组遍历的指针
    // newIndex 新数组插入位置的指针
    public static int removeDuplicates(int[] nums) {

        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        int oldIndex = 1;
        int newIndex = 1;
        while (oldIndex < n) {
            if (nums[newIndex - 1] != nums[oldIndex]) {
                nums[newIndex] = nums[oldIndex];
                newIndex++;
            }
            oldIndex++;
        }
        return newIndex;
    }
}
