package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 15:09:01
 */
// https://leetcode.cn/problems/subsets-ii/description/
// 90. 子集II
// 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
//
// 示例 1：
//
// 输入：nums = [1,2,2]
// 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 示例 2：
//
// 输入：nums = [0]
// 输出：[[],[0]]
public class SubCollection2 {

    private static final List<List<Integer>> result = new ArrayList<>();
    private static final LinkedList<Integer> sub = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = subsetsWithDup(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        if (null == nums || nums.length == 0) {
            return result;
        }
        result.clear();
        sub.clear();
        Arrays.sort(nums);
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
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            sub.add(nums[i]);
            backtracking(nums, i + 1);
            sub.removeLast();
        }
    }
}


