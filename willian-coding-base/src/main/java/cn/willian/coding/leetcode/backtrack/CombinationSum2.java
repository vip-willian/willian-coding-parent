package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 10:01:55
 */
// https://leetcode.cn/problems/combination-sum-ii/description/
// 40. 组合总和II

// 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
// candidates 中的每个数字在每个组合中只能使用 一次 。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
// 输出:
// [
// [1,1,6],
// [1,2,5],
// [1,7],
// [2,6]
// ]
// 示例 2:
//
// 输入: candidates = [2,5,2,1,2], target = 5,
// 输出:
// [
// [1,2,2],
// [5]
// ]
public class CombinationSum2 {

    private static final List<List<Integer>> RESULT = new ArrayList<>();
    private static final LinkedList<Integer> PATH = new LinkedList<>();

    public static void main(String[] args) {
        List<List<Integer>> result = combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target < 0) {
            return RESULT;
        }
        RESULT.clear();
        PATH.clear();

        boolean[] used = new boolean[candidates.length];
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        Arrays.fill(used, false);

        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backtracking(target, candidates, used, 0, 0);
        return RESULT;
    }

    public static void backtracking(int target, int[] candidates, boolean[] used, int sum, int startIndex) {

        if (sum == target) {
            RESULT.add(new ArrayList<>(PATH));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {

            int value = candidates[i];
            // 处理重复
            if (i > startIndex && value == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            // 处理
            PATH.add(value);
            sum += value;
            used[i] = true;

            backtracking(target, candidates, used, sum, i + 1);

            // 回溯
            PATH.removeLast();
            sum -= value;
            used[i] = false;
        }
    }
}
