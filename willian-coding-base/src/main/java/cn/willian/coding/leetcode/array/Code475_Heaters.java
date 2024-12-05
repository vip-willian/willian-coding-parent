package cn.willian.coding.leetcode.array;

import java.util.Arrays;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 21:29:06
 */
// https://leetcode.cn/problems/heaters/description/
// 475. 供暖器
// 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
// 在加热器的加热半径范围内的每个房屋都可以获得供暖。
// 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
// 注意：所有供暖器 heaters 都遵循你的半径标准，加热的半径也一样。

// 示例 1:
// 输入: houses = [1,2,3], heaters = [2]
// 输出: 1
// 解释: 仅在位置 2 上有一个供暖器。如果我们将加热半径设为 1，那么所有房屋就都能得到供暖。
//
// 示例 2:
// 输入: houses = [1,2,3,4], heaters = [1,4]
// 输出: 1
// 解释: 在位置 1, 4 上有两个供暖器。我们需要将加热半径设为 1，这样所有房屋就都能得到供暖。

// 示例 3：
// 输入：houses = [1,5], heaters = [2]
// 输出：3
public class Code475_Heaters {
    public static void main(String[] args) {
        int[] houses = {1, 2, 3, 4};
        int[] heaters = {1, 4};
        int radius = findRadius(houses, heaters);
        System.out.println(radius);
    }

    public static int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        // i号房屋
        // j号供暖器
        for (int i = 0, j = 0; i < houses.length; i++) {
            // i号房屋选择j号供暖器的位置不是最优位置
            while (!best(houses, heaters, i, j)) {
                // 供暖器位置移动
                j++;
            }
            // 获取位置半径的最大值
            ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
        }
        return ans;
    }

    private static boolean best(int[] houses, int[] heaters, int i, int j) {
        // j号位置供暖器获得的半径比j+1号位置供暖器获得半径小
        return j == heaters.length - 1 || Math.abs(heaters[j] - houses[i]) < Math.abs(heaters[j + 1] - houses[i]);
    }
}
