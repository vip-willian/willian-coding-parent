package cn.willian.coding.leetcode.greedy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 09:39:45
 */
// https://leetcode.cn/problems/jump-game/description
// 55. 跳跃游戏
// 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
// 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。

// 示例 1：
// 输入：nums = [2,3,1,1,4]
// 输出：true
// 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

// 示例 2：
// 输入：nums = [3,2,1,0,4]
// 输出：false
// 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
public class Code55_JumpGame {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {

        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        // 维护一个在当前位置能够跳跃的最右位置
        int mostRight = 0;
        for (int i = 0; i < n; i++) {
            // 当前位置 已经在能跳跃到最大位置之后，返回false
            if (i <= mostRight) {
                // 更新每次能够跳跃到的最大位置
                mostRight = Math.max(mostRight, i + nums[i]);
                // 如果能够跳跃到的最大位置 大于或等于数组的最后一个位置，说明能够到达
                if (mostRight >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
