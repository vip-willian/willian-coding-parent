package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 10:01:55
 */
// https://leetcode.cn/problems/combination-sum-iii/description/
// 216. 组合总和 III
// 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
//
// 只使用数字1到9
// 每个数字 最多使用一次
// 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
//
//
//
// 示例 1:
//
// 输入: k = 3, n = 7
// 输出: [[1,2,4]]
// 解释:
// 1 + 2 + 4 = 7
// 没有其他符合的组合了。
// 示例 2:
//
// 输入: k = 3, n = 9
// 输出: [[1,2,6], [1,3,5], [2,3,4]]
// 解释:
// 1 + 2 + 6 = 9
// 1 + 3 + 5 = 9
// 2 + 3 + 4 = 9
// 没有其他符合的组合了。
// 示例 3:
//
// 输入: k = 4, n = 1
// 输出: []
// 解释: 不存在有效的组合。
// 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
public class CombinationSum3 {

    private static final List<List<Integer>> RESULT = new ArrayList<>();
    private static final LinkedList<Integer> PATH = new LinkedList<>();

    public static void main(String[] args) {
        List<List<Integer>> result = combine(9, 3);
        System.out.println(result);
    }

    public static List<List<Integer>> combine(int n, int k) {

        RESULT.clear();
        PATH.clear();
        backtracking(n, k, 0, 1);
        return RESULT;
    }

    // targetNum: 目标和
    // k: k个数
    // sum: 当前和
    // startIndex: 开始位置
    public static void backtracking(int targetNum, int k, int sum, int startIndex) {

        // 提前剪枝
        if (sum > targetNum) {
            return;
        }
        if (PATH.size() == k) {
            if (sum == targetNum) {
                RESULT.add(new ArrayList<>(PATH));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - PATH.size()) + 1; i++) {
            // 处理
            PATH.add(i);
            sum += i;

            backtracking(targetNum, k, sum, i + 1);

            // 回溯
            sum -= i;
            PATH.removeLast();
        }
    }
}
