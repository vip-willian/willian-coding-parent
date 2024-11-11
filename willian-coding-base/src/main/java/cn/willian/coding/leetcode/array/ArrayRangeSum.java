package cn.willian.coding.leetcode.array;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-05 12:54:22
 */
// 给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
// 输入描述
// 第一行输入为整数数组 Array 的长度 n，接下来 n 行，每行一个整数，表示数组的元素。随后的输入为需要计算总和的区间，直至文件结束。
// 输出描述
// 输出每个指定区间内元素的总和。
public class ArrayRangeSum {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数组长度:");
        int length = scanner.nextInt();

        int[] array = new int[length];
        int[] sums = new int[length];

        int sum = 0;
        System.out.println("请输入长度为[" + length + "]数组元素:");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
            sum += array[i];
            sums[i] = sum;
        }
        System.out.println("请输入2个区间下标:");
        while (scanner.hasNext()) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            int result = 0;
            if (start == 0) {
                result = sums[end];
            } else {
                result = sums[end] - sums[start];
            }
            System.out.println("该下标区间和为：" + result);
        }
        scanner.close();
    }
}
