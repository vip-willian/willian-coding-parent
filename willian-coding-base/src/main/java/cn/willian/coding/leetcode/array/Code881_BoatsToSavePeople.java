package cn.willian.coding.leetcode.array;

import java.util.Arrays;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-05 17:54:39
 */
// https://leetcode.cn/problems/boats-to-save-people/description/
// 881. 救生艇
// 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
// 返回 承载所有人所需的最小船数 。

// 示例 1：
// 输入：people = [1,2], limit = 3
// 输出：1
// 解释：1 艘船载 (1, 2)

// 示例 2：
// 输入：people = [3,2,2,1], limit = 3
// 输出：3
// 解释：3 艘船分别载 (1, 2), (2) 和 (3)

// 示例 3：
// 输入：people = [3,5,3,4], limit = 5
// 输出：4
// 解释：4 艘船分别载 (3), (3), (4), (5)
public class Code881_BoatsToSavePeople {

    public static void main(String[] args) {
        int[] people = {3, 5, 3, 4};
        int limit = 5;
        System.out.println(numRescueBoats(people, limit));
    }

    public static int numRescueBoats(int[] people, int limit) {

        // 先按体重排序
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;

        int ans = 0;
        while (left <= right) {
            int sum = left == right ? people[left] : people[left] + people[right];
            // 左边最小体重的人 + 右边最大体重的人 没超过限重 可以坐一艘船走
            if (sum <= limit) {
                left++;
            }
            // 最重的人只能单独一艘船
            right--;
            ans++;
        }
        return ans;
    }
}
