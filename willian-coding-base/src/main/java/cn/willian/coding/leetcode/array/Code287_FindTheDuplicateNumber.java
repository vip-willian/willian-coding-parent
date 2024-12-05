package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 17:10:46
 */
// https://leetcode.cn/problems/find-the-duplicate-number/description/
// 287. 寻找重复数
// 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

// 示例 1：
// 输入：nums = [1,3,4,2,2]
// 输出：2

// 示例 2：
// 输入：nums = [3,1,3,4,2]
// 输出：3

// 示例 3 :
// 输入：nums = [3,3,3,3,3]
// 输出：3
public class Code287_FindTheDuplicateNumber {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        int number = findDuplicate(nums);
        System.out.println(number);
    }

    public static int findDuplicate(int[] nums) {

        if (nums == null || nums.length < 2) {
            return -1;
        }
        // 快慢指针解法
        // 慢指针一次走1步
        int slow = nums[0];
        // 快指针一次走2步
        int fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 相等之后确认慢指针的位置
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
