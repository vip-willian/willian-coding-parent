package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-30 09:56:45
 */
// 输入 n 个整型数，统计其中的负数个数并求所有非负数的平均值，结果保留一位小数，如果没有非负数，则平均值为0
// 本题有多组输入数据，输入到文件末尾。
// 数据范围：1 \le n \le 50000 \1≤n≤50000 ，其中每个数都满足 |val| \le 10^{6} \∣val∣≤106
//
// 输入描述：
// 输入任意个整数，每行输入一个。
//
// 输出描述：
// 输出负数个数以及所有非负数的平均值

// 输入：
// -13
// -4
// -7
// 输出：
// 3
// 0.0
public class NegativeAvgPositiveII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 负数的个数
        int negativeCount = 0;
        // 非负数的个数
        int notNegativeCount = 0;
        // 非负数的综合
        int notNegativeSum = 0;
        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            if (num >= 0) {
                notNegativeCount++;
                notNegativeSum += num;
            } else {
                negativeCount++;
            }
        }
        System.out.println(negativeCount);
        System.out
            .println(notNegativeCount == 0 ? 0.0 : String.format("%.1f", (double)notNegativeSum / notNegativeCount));
    }
}
