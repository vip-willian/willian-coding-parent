package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 15:25:52
 */
// https://leetcode.cn/problems/non-decreasing-subsequences/description/
// 491. 非递减子序列
// 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
// 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
//
// 示例 1：
//
// 输入：nums = [4,6,7,7]
// 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
// 示例 2：
//
// 输入：nums = [4,4,3,2,1]
// 输出：[[4,4]]
public class NonDecreasingSubsequences {

    private static final List<List<Integer>> result = new ArrayList<>();
    private static final LinkedList<Integer> sub = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> result = findSubsequences(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {

        if (null == nums || nums.length == 0) {
            return result;
        }
        result.clear();
        sub.clear();
        backtracking(nums, 0);
        return result;
    }

    private static void backtracking(int[] nums, int startIndex) {

        if (sub.size() >= 2) {
            result.add(new ArrayList<>(sub));
        }
        HashSet<Integer> hs = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!sub.isEmpty() && sub.getLast() > nums[i] || hs.contains(nums[i])) {
                continue;
            }
            hs.add(nums[i]);
            sub.add(nums[i]);
            backtracking(nums, i + 1);
            sub.removeLast();
        }
    }
}
