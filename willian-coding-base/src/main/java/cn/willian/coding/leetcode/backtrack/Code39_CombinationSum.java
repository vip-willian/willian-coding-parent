package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 10:01:55
 */
// https://leetcode.cn/problems/combination-sum/description/
// 39. 组合总和
// 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
//
// 示例 1：
// 输入：candidates = [2,3,6,7], target = 7
// 输出：[[2,2,3],[7]]
// 解释：
// 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
// 7 也是一个候选， 7 = 7 。
// 仅有这两种组合。

// 示例 2：
// 输入: candidates = [2,3,5], target = 8
// 输出: [[2,2,2,2],[2,3,3],[3,5]]

// 示例 3：
// 输入: candidates = [2], target = 1
// 输出: []
public class Code39_CombinationSum {

    private static final List<List<Integer>> result = new ArrayList<>();
    private static final LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        List<List<Integer>> result = combinationSum(new int[] {2, 3, 6, 7}, 7);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target < 0) {
            return result;
        }
        result.clear();
        path.clear();
        backtracking(target, candidates, 0, 0);
        return result;
    }

    // target: 目标和
    // sum: 当前和
    // startIndex: 开始位置
    public static void backtracking(int target, int[] candidates, int sum, int startIndex) {

        // 提前剪枝
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            // 处理
            int value = candidates[i];
            path.add(value);
            sum += value;

            backtracking(target, candidates, sum, i);

            // 回溯
            sum -= value;
            path.removeLast();
        }
    }
}
