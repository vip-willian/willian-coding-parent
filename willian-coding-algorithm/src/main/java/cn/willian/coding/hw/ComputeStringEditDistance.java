package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 21:50:29
 */
// 描述
// Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，
// 插入一个字符，删除一个字符。编辑距离的算法是首先由俄国科学家
// Levenshtein 提出的，故又叫 Levenshtein Distance 。
//
// 例如：
//
// 字符串A: abcdefg
// 字符串B: abcdef
//
// 通过增加或是删掉字符 ”g” 的方式达到目的。这两种方案都需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
//
// 要求：
//
// 给定任意两个字符串，写出一个算法计算它们的编辑距离。
// 数据范围：给定的字符串长度满足
// 1000
//
// 1≤len(str)≤1000
//
// 输入描述：
// 每组用例一共2行，为输入的两个字符串
//
// 输出描述：
// 每组用例输出一行，代表字符串的距离
//
// 示例1
// 输入：
// abcdefg
// abcdef
// 复制
// 输出：
// 1
public class ComputeStringEditDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            System.out.println(getEditDistance(a, b));
        }
    }

    private static int getEditDistance(String a, String b) {

        int m = a.length();
        int n = b.length();

        // dp[i][j]表示A串从第0个字符开始到第i个字符 和 B串从第0个字符开始到第j个字符 的编辑距离
        int[][] dp = new int[m][n];
        // A串为空，B串为空 编辑距离为0
        dp[0][0] = 0;
        // A串不为空，B串为空 删除对应i次字符
        for (int i = 1; i < m; i++) {
            dp[i][0] = i;
        }
        // A串为空，B串不为空 增加对应j次字符
        for (int j = 1; j < n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 第二种方案 i位置字符 != j位置字符 插入 dp[i][j-1] + 1
                int insertCase = dp[i][j - 1] + 1;
                // 第三种方案 i位置字符 != j位置字符 删除 dp[i-1][j] + 1
                int deleteCase = dp[i - 1][j] + 1;
                // 第一种方案 i位置字符 == j位置字符 dp[i-1][j-1]
                // 第四种方案 i位置字符 != j位置字符 替换 dp[i-1][j-1] + 1
                int replaceCase = (a.charAt(i) == b.charAt(j) ? 0 : 1) + dp[i - 1][j - 1];
                dp[i][j] = Math.min(Math.min(insertCase, deleteCase), replaceCase);
            }
        }
        return dp[m - 1][n - 1];
    }
}
