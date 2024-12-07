package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 16:53:46
 */
// https://leetcode.cn/problems/longest-common-subsequence/description/
// 1143. 最长公共子序列
// 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
//
//
// 示例 1：
//
// 输入：text1 = "abcde", text2 = "ace"
// 输出：3
// 解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 示例 2：
//
// 输入：text1 = "abc", text2 = "abc"
// 输出：3
// 解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 示例 3：
//
// 输入：text1 = "abc", text2 = "def"
// 输出：0
// 解释：两个字符串没有公共子序列，返回 0 。
public class Code1143_LongestCommonSubSequence {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence1(text1, text2));
        System.out.println(longestCommonSubsequence2(text1, text2));
        System.out.println(longestCommonSubsequence3(text1, text2));
    }

    public static int longestCommonSubsequence1(String text1, String text2) {

        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        return process1(t1, t2, t1.length - 1, t2.length - 1);
    }

    public static int longestCommonSubsequence2(String text1, String text2) {

        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        return process2(t1, t2, t1.length, t2.length);
    }

    public static int longestCommonSubsequence3(String text1, String text2) {

        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int m = t1.length;
        int n = t2.length;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (t1[i - 1] == t2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    // 从[0....i字符]、[0....j字符]最长公共子序列
    private static int process1(char[] t1, char[] t2, int i, int j) {

        if (i < 0 || j < 0) {
            return 0;
        }
        // 第一种可能性：最长公共子序列既不以i位置字符结尾，也不以j位置字符结尾
        int p1 = process1(t1, t2, i - 1, j - 1);
        // 第二种可能性：最长公共子序列以i位置字符结尾，不以j位置字符结尾
        int p2 = process1(t1, t2, i, j - 1);
        // 第三种可能性：最长公共子序列不以i位置字符结尾，以j位置字符结尾
        int p3 = process1(t1, t2, i - 1, j);
        // 第四种可能性：最长公共子序列既以i位置字符结尾，又以j位置字符结尾
        int p4 = t1[i] == t2[j] ? p1 + 1 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // 从t1[前缀长度为len1]、t2[前缀长度为len2]最长公共子序列
    private static int process2(char[] t1, char[] t2, int len1, int len2) {

        if (len1 == 0 || len2 == 0) {
            return 0;
        }
        int ans;
        if (t1[len1 - 1] == t2[len2 - 1]) {
            ans = process2(t1, t2, len1 - 1, len2 - 1) + 1;
        } else {
            ans = Math.max(process2(t1, t2, len1, len2 - 1), process2(t1, t2, len1 - 1, len2));
        }
        return ans;
    }
}
