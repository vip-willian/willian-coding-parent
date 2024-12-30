package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 21:18:11
 */

// 现有一组砝码，重量互不相等，分别为 m1,m2,m3…mn ；
// 每种砝码对应的数量为 x1,x2,x3…xn 。现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
// 注：
//
// 称重重量包括 0
//
// 本题有多组输入
//
// 输入描述：
// 输入包含多组测试数据。
// 对于每组测试数据：
// 第一行：n — 砝码数(范围[1,10])
// 第二行：m1 m2 m3 … mn — 每个砝码的重量(范围[1,2000])
// 第三行：x1 x2 x3 … xn — 每个砝码的数量(范围[1,6])
// 输出描述：
// 利用给定的砝码可以称出的不同的重量数

// 示例1:
// 2
// 1 2
// 2 1
// 输出：5

// 示例2:
// 10
// 4 185 35 191 26 148 149 3 172 147
// 3 5 2 1 5 5 3 1 4 2
// 输出: 3375
public class ChengWeight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 砝码个数
            int n = sc.nextInt();
            // 重量数组
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = sc.nextInt();
            }
            // 数量数组
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(diffWeightNum(weights, nums));
        }
    }

    private static Integer diffWeightNum(int[] weights, int[] nums) {

        Set<Integer> diffWeight = new HashSet<>();
        // 第一个砝码，取0个 到取N 个不同的重量
        for (int i = 0; i <= nums[0]; i++) {
            diffWeight.add(i * weights[0]);
        }
        // 从第二个砝码开始
        for (int i = 1; i < weights.length; i++) {
            List<Integer> path = new ArrayList<>(diffWeight);
            for (int j = 0; j <= nums[i]; j++) {
                for (Integer oldWeight : path) {
                    diffWeight.add(oldWeight + j * weights[i]);
                }
            }
        }
        return diffWeight.size();
    }
}
