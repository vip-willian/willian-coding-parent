package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 15:09:01
 */
// https://leetcode.cn/problems/subsets/
// 78. 子集
// 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
// 解集不能包含重复的子集。你可以按 任意顺序 返回解集。
//
// 示例 1：
//
// 输入：nums = [1,2,3]
// 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 示例 2：
//
// 输入：nums = [0]
// 输出：[[],[0]]
public class SubCollection {

    private static final List<List<Integer>> result = new ArrayList<>();
    private static final LinkedList<Integer> sub = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> subsets(int[] nums) {

        if (null == nums || nums.length == 0) {
            return result;
        }
        result.clear();
        sub.clear();
        backtracking(nums, 0);
        return result;
    }

    private static void backtracking(int[] nums, int startIndex) {

        // 收集全部的结果
        result.add(new ArrayList<>(sub));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            sub.add(nums[i]);
            backtracking(nums, i + 1);
            sub.removeLast();
        }
    }
}
