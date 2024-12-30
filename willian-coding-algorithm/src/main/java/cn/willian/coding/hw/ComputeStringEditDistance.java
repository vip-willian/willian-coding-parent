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
            String source = sc.nextLine();
            String target = sc.nextLine();
            // 获取字符串的编辑距离
            System.out.println(getEditDistance(source, target));
        }
    }

    private static Integer getEditDistance(String source, String target) {

        int m = source.length();
        int n = target.length();
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        // source字符为空,target字符不为空
        for (int j = 1; j <= n; j++) {
            // 增加j次对应的字符次数
            dp[0][j] = j;
        }
        // target字符为空,source字符不为空
        for (int i = 1; i <= n; i++) {
            // 删除i次对应的字符次数
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果source 和 target 两位位置字符相等
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    // 等于 source i - 1 和 target j - 1 位置的编辑距离
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(
                        // 第一种方式: 删除i-1位置的字符
                        dp[i - 1][j] + 1,
                        // 第二种方式: 增加j-1位置的字符
                        dp[i][j - 1] + 1),
                        // 第三种方式: 替换i-1和j-1位置的字符,保持相等
                        dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
