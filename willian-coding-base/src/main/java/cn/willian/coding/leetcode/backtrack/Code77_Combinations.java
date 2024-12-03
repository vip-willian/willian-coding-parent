package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 00:14:44
 */
// https://leetcode.cn/problems/combinations/description
// 77. 组合
// 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 你可以按 任何顺序 返回答案。
//
// 示例 1：
// 输入：n = 4, k = 2
// 输出：
// [[2,4],
// [3,4],
// [2,3],
// [1,2],
// [1,3],
// [1,4]]
//
// 示例 2：
// 输入：n = 1, k = 1
// 输出：[[1]]
public class Code77_Combinations {

    private static final List<List<Integer>> result = new ArrayList<>();
    private static final LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        List<List<Integer>> result = combine(4, 2);
        System.out.println(result);
    }

    public static List<List<Integer>> combine(int n, int k) {

        result.clear();
        path.clear();
        backtracking(n, k, 1);
        return result;
    }

    private static void backtracking(int n, int k, int index) {

        // 元素个数已经达到k个，进行收集
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 可搜索的上限位置，假如k = 2,但是只有1个元素，没必要在进行搜索
        // k - path.size() 已选择的元素个数
        // 可搜索位置的上限 = 总个数 - 已选择的 + 1
        // n - (k - path.size()) + 1
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }
}
