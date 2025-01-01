package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2025-01-01 10:34:33
 */
// 题目描述：
// 给定一个数组，里面有6个整数，求这个数组能够表示的最大24进制的时间是多少，输出这个时间，无法表示输出invalid
// 输入描述：
// 输入为一个整数数组，数组内有6个整数
// 输入整数数组长度为6，不需要考虑其它长度，元素值为0或者正整数，6个数字每个数字只能使用一次。
// 输出描述：
// 输出为一个24进账格式的时间，或者字符串“invalid”
//
// 示例1：
// 输入
// [0,2,3,0,5,6]
// 输出:
// 23:56:00
public class MaxTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 获取输入的数据，放入数组中
            int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            // 最大时间
            System.out.println(getMaxTime(nums));
        }
        sc.close();
    }

    private static String getMaxTime(int[] nums) {

        // 获取nums所有组合可能性
        List<int[]> permutations = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        // 生成所有排列的可能性
        generationPermutations(nums, new int[6], 0, visited, permutations);

        int maxTimeInSeconds = -1;
        String maxTime = "invalid";
        // 遍历所有的排列，找出有效时间并记录最大的
        for (int[] perm : permutations) {
            int hour = perm[0] * 10 + perm[1];
            int minute = perm[2] * 10 + perm[3];
            int second = perm[4] * 10 + perm[5];
            // 检查时间是否有效
            if (isValidTime(hour, minute, second)) {
                // 计算总秒数
                int totalSeconds = hour * 60 * 60 + minute * 60 + second;
                // 更新最大时间
                if (totalSeconds > maxTimeInSeconds) {
                    maxTimeInSeconds = totalSeconds;
                    // 格式化时间字符串
                    maxTime = String.format("%02d:%02d:%02d", hour, minute, second);
                }
            }
        }
        return maxTime;
    }

    /**
     * 校验时间是否有效
     * 
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @return 时间是否有效
     */
    private static boolean isValidTime(int hour, int minute, int second) {

        // 小时范围不合法
        if (hour < 0 || hour > 23) {
            return false;
        }
        // 分钟范围不合法
        if (minute < 0 || minute > 59) {
            return false;
        }
        // 秒范围不合法
        if (second < 0 || second > 59) {
            return false;
        }
        return true;
    }

    /**
     * 递归生成所有排列组合
     * 
     * @param nums 原始数组
     * @param current 当前排列
     * @param index 当前填充的位置
     * @param visited 标记哪些数字已经被使用
     * @param permutations 存储所有的排列
     */
    private static void generationPermutations(int[] nums, int[] current, int index, boolean[] visited,
        List<int[]> permutations) {

        // base case
        int n = nums.length;
        // 当排列长度达到6时，添加到列表中
        if (index == n) {
            permutations.add(current.clone());
            return;
        }
        // 遍历所有数字，生成排列
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 标记为已使用
                visited[i] = true;
                // 选择当前数字
                current[index] = nums[i];
                // 递归选择下一个数字
                generationPermutations(nums, current, index + 1, visited, permutations);
                // 回溯，取消选择
                visited[i] = false;
            }
        }
    }
}
