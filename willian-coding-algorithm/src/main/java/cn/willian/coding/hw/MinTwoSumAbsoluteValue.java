package cn.willian.coding.hw;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 11:38:08
 */
// 序号7
// 标题：两数之和绝对值最小
// 给定一个从小到大的有序整数序列（存在正整数和负整数）数组nums，请你在该数
// 字中找出两个数，其和的绝对值（|nums[x]+nums[y]|)为最小值,并返回这个绝对值。
// 每种输入只会对应一个答案，但是数组中同一个元素不能使用两遍。
// 一个通过空格分割的有序整数序列字符串，最多1000个整数，且整数数值范围内是-65535到～65535。
//
// 输入描述：
//
// 输出描述：
// 两数之和绝对值最小值
//
// 示例1：
// 输入：
// -3 -1 5 7 11 15
//
// 输出
// 2

public class MinTwoSumAbsoluteValue {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        // 获取输入的数并进行排序
        List<Integer> nums =
            Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());
        int n = nums.size();

        // 双指针
        int left = 0;
        int right = n - 1;
        int a = nums.get(left);
        int b = nums.get(right);
        int minValue = Math.abs(nums.get(left) + nums.get(right));

        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            // 更新最小绝对值
            if (Math.abs(sum) < minValue) {
                minValue = Math.abs(sum);
                a = nums.get(left);
                b = nums.get(right);
            }
            // 两数之和大于0 right左移
            if (sum > 0) {
                right--;
                // 两数之和小于0 left右移
            } else if (sum < 0) {
                left++;
                // 等于0找到最小，退出
            } else {
                break;
            }
        }
        System.out.println(a + " " + b + " " + minValue);
    }
}
