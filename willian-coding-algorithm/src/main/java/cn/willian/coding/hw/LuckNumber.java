package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-31 17:19:02
 */
// 题目描述
// 有位客人来自异国，在该国使用m进制计数。
//
// 该客人有个幸运数字n(n<m)，每次购物时，其总是喜欢计算本次支付的花费(折算为异国的价格后)中存在多少幸运数字。
//
// 问: 当其购买一个在我国价值k的产品时，其中包含多少幸运数字?
//
// 输入描述
// 第一行输入为 k,n,m。其中:
//
// k 表示 该客人购买的物品价值 (以十进制计算的价格)
//
// n 表示该客人的幸运数字
//
// m 表示 该客人所在国度的采用的进制
//
// 输出描述
// 输出幸运数字的个数，行未无空格。
//
// 示例1
// 输入：
// 10 2 4
//
// 输出：
// 2
//
// 说明：
// 10用4进制表示时为22，同时，异国客人的幸运数字是2，故而此处输出为2，表示有2个幸运数字。
// 示例2
// 输入：
// 10 4 4
//
// 输出：
// 0
//
// 说明：
// 此时客人的幸运数字为4，但是由于该国最大为4进制，故而在该国的进制下不可能出现幸运数字，故而返回0
public class LuckNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            // 购买的物品价值 十进制
            int k = sc.nextInt();
            // 幸运数字
            int n = sc.nextInt();
            // 目标进制
            int m = sc.nextInt();

            // 进制转换
            String number = convertScale(k, m);
            // 统计幸运数字
            int count = statisticsLucyNumber(number, n);
            System.out.println(count);
        }
        sc.close();
    }

    // 将十进制数 k 转换成 m进制数
    private static String convertScale(int k, int m) {

        StringBuilder sb = new StringBuilder();
        while (k > 0) {
            // 取余数
            int remainder = k % m;
            sb.append(remainder);
            k = k / m;
        }
        return sb.reverse().toString();
    }

    // 统计 m 进制表示中 幸运数字 n 的个数
    private static int statisticsLucyNumber(String number, int n) {

        int count = 0;
        // 将int类型转换为char类型
        char lucyDigit = Character.forDigit(n, 10);
        for (char digit : number.toCharArray()) {
            if (digit == lucyDigit) {
                count++;
            }
        }
        return count;
    }
}
