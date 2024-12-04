package cn.willian.coding.leetcode.greedy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 09:39:45
 */
// https://leetcode.cn/problems/jump-game-ii/description
// 45. 跳跃游戏II
// 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
// 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
// 0 <= j <= nums[i]
// i + j < n
// 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。

// 示例 1:
// 输入: nums = [2,3,1,1,4]
// 输出: 2
// 解释: 跳到最后一个位置的最小跳跃数是 2。 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

// 示例 2:
// 输入: nums = [2,3,0,1,4]
// 输出: 2
public class Code45_JumpGame2 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jump(nums));
    }

    // 贪心策略：每一次选择的下标位置时下一次能够跳的更远的位置
    public static int jump(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 选择每次下标能够跳的最远位置index
        int maxPosition = 0;
        // 上一次能够跳的最大位置
        int end = 0;
        // 跳跃步数
        int step = 0;

        for (int i = 0; i < n - 1; i++) {
            // 获取能够跳跃的最远位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 判断我当前位置是否已经到达了上次执行的最远位置，开始下一次选择
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }
}
