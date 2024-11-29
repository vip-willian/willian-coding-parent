package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 10:01:55
 */
// https://leetcode.cn/problems/combinations/
// 77 组合
// 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
// 你可以按 任何顺序 返回答案。
//
// 示例 1：
//
// 输入：n = 4, k = 2
// 输出：
// [
// [2,4],
// [3,4],
// [2,3],
// [1,2],
// [1,3],
// [1,4],
// ]
// 示例 2：
//
// 输入：n = 1, k = 1
// 输出：[[1]]
public class Combination {

    public static void main(String[] args) {
        List<List<Integer>> result = combine(4, 2);
        System.out.println(result);
    }

    private static final List<List<Integer>> RESULT = new ArrayList<>();
    private static final LinkedList<Integer> PATH = new LinkedList<>();



    public static List<List<Integer>> combine(int n, int k) {

        RESULT.clear();
        PATH.clear();
        backtracking(n, k, 1);
        return RESULT;
    }

    public static void backtracking(int n, int k, int startIndex) {

        if (PATH.size() == k) {
            RESULT.add(new ArrayList<>(PATH));
            return;
        }
        for (int i = startIndex; i <= n - (k - PATH.size()) + 1; i++) {
            PATH.add(i);
            backtracking(n, k, i + 1);
            PATH.removeLast();
        }
    }
}
