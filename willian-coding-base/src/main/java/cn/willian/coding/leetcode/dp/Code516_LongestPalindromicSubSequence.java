package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 21:19:33
 */
// https://leetcode.cn/problems/longest-palindromic-subsequence/description/
// 516. 最长回文子序列
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。

// 示例 1：
// 输入：s = "bbbab"
// 输出：4
// 解释：一个可能的最长回文子序列为 "bbbb" 。

// 示例 2：
// 输入：s = "cbbd"
// 输出：2
// 解释：一个可能的最长回文子序列为 "bb" 。
//
// 提示：
//
// 1 <= s.length <= 1000
// s 仅由小写英文字母组成
public class Code516_LongestPalindromicSubSequence {

    // 区间dp
    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubSeq1(s));
        System.out.println(longestPalindromeSubSeq2(s));
    }

    // s[L ~ R]范围上的最长回文子序列
    public static int longestPalindromeSubSeq1(String s) {

        char[] str = s.toCharArray();
        int n = s.length();
        return process1(str, 0, n - 1);
    }

    public static int longestPalindromeSubSeq2(String s) {

        char[] str = s.toCharArray();
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int l = n - 1; l >= 0; l--) {
            // 对角线全为1，当left==right时
            dp[l][l] = 1;
            // 只剩余2个字符串时
            if (l + 1 < n) {
                dp[l][l + 1] = str[l] == str[l + 1] ? 2 : 1;
            }
            // 其他位置
            for (int r = l + 2; r < n; r++) {
                if (str[l] == str[r]) {
                    // 左右字符相同的场景, 此时回文子串是最长的
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    // 回文子串在（L+1,R）(L,R-1)之间的最大长度
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    // s[L ~ R]范围上的最长回文子序列
    private static int process1(char[] str, int left, int right) {
        // 左右位置相同，最长回文子序列为1
        if (left == right) {
            return 1;
        }
        // 剩余2个字符串
        if (left + 1 == right) {
            return str[left] == str[right] ? 2 : 1;
        }
        int ans;
        if (str[left] == str[right]) {
            // 左右字符相同的场景, 此时回文子串是最长的
            ans = process1(str, left + 1, right - 1) + 2;
        } else {
            // 回文子串在（L+1,R）(L,R-1)之间的最大长度
            ans = Math.max(process1(str, left + 1, right), process1(str, left, right - 1));
        }
        return ans;
    }
}
