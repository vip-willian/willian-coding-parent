package cn.willian.coding.leetcode.array;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 17:23:36
 */
// https://leetcode.cn/problems/trapping-rain-water/description/
// 42. 接雨水
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

// 示例 1：
// 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
// 输出：6
// 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

// 示例 2：
// 输入：height = [4,2,0,3,2,5]
// 输出：9
public class Code42_TrappingRainWater {

    public static void main(String[] args) {

        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = trap(heights);
        System.out.println(trap);
    }

    // i位置上能留下多少水 = max(min(左侧最大高度,右侧最大高度) - i位置的高度,0)
    public static int trap(int[] height) {

        // 0位置不会留下水量
        int left = 1;
        // 左边的位置最大高度
        int leftMax = height[0];
        // n-1位置不会留下水量
        int right = height.length - 2;
        // 右边的位置最大高度
        int rightMax = height[height.length - 1];

        int ans = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                // left位置水量 = max(左右最小值 - left位置高度,0)
                ans += Math.max(leftMax - height[left], 0);
                leftMax = Math.max(leftMax, height[left++]);
            } else {
                // right位置水量 = max(左右最小值 - right位置高度,0)
                ans += Math.max(rightMax - height[right], 0);
                rightMax = Math.max(rightMax, height[right--]);
            }
        }
        return ans;
    }
}
