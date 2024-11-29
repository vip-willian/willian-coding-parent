package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 15:46:47
 */
// https://leetcode.cn/problems/permutations-ii/description/
// 47. 全排列II
// 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 示例 1：
// 输入：nums = [1,1,2]
// 输出：
// [[1,1,2],
// [1,2,1],
// [2,1,1]]

// 示例 2：
// 输入：nums = [1,2,3]
// 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
public class Permutations {

    private static final List<List<Integer>> result = new ArrayList<>();
    private static final LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permuteUnique(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {

        if (null == nums || nums.length == 0) {
            return result;
        }
        result.clear();
        path.clear();
        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);

        backtracking(nums, used);
        return result;
    }

    private static void backtracking(int[] nums, boolean[] used) {

        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == false 说明同⼀树层nums[i - 1]使⽤过
            // used[i - 1] == true 说明同⼀树⽀nums[i - 1]使⽤过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 同一个树枝没使用过
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                backtracking(nums, used);
                path.removeLast();
                used[i] = false;
            }
        }
    }
}
