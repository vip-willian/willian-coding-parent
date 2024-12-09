package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-08 10:03:09
 */
// https://leetcode.cn/problems/interleaving-string/description/
// 97. 交错字符串
// 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空子字符串
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
// 注意：a + b 意味着字符串 a 和 b 连接。

// 示例 1：
// 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// 输出：true

// 示例 2：
// 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// 输出：false

// 示例 3：
// 输入：s1 = "", s2 = "", s3 = ""
// 输出：true
public class Code97_InterLeavingString {

    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        System.out.println(isInterleave(s1, s2, s3));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {

        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        // dp[i][j]含义:s1[前i个字符串]s2[[前j个字符串] 能否交错组成 s3[前i+j个字符串]
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // base case分析
        // 第0行 全为true
        for (int i = 1; i <= n; i++) {
            if (c2[i - 1] != c3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        // 第0列 全为true
        for (int j = 1; j <= m; j++) {
            if (c1[j - 1] != c3[j - 1]) {
                break;
            }
            dp[j][0] = true;
        }
        // 普遍位置依赖分析
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 1.1、如果s1[i-1]的字符串 == s3[i+j-1]的字符串，说明是由s1组成的最后一个字符串，那么再判断 s1[前i-1个字符串]s2[[前j个字符串] 能否交错组成
                // s3[前i+j-1个字符串]
                // 1.2、如果s2[j-1]的字符串 == s3[i+j-1]的字符串，说明是由s2组成的最后一个字符串，那么再判断 s1[前i个字符串]s2[[前j-1个字符串] 能否交错组成
                // s3[前i+j-1个字符串]
                dp[i][j] = (c1[i - 1] == c3[i + j - 1] && dp[i - 1][j]) || (c2[j - 1] == c3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
