package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 14:46:33
 */
// https://leetcode.cn/problems/distinct-subsequences-ii/description/
// 940. 不同的子序列 II
// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
// 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。

// 示例 1：
// 输入：s = "abc"
// 输出：7
// 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。

// 示例 2：
// 输入：s = "aba"
// 输出：6
// 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。

// 示例 3：
// 输入：s = "aaa"
// 输出：3
// 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
public class Code940_DistinctSubSequences2 {

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(distinctSubSeq(s));
    }

    public static int distinctSubSeq(String s) {

        int mod = 1000000007;
        char[] str = s.toCharArray();
        int[] dp = new int[26];
        int all = 1, newAdd;
        for (char c : str) {
            newAdd = (all - dp[c - 'a'] + mod) % mod;
            dp[c - 'a'] = (dp[c - 'a'] + newAdd) % mod;
            all = (all + newAdd) % mod;
        }
        return (all - 1 + mod) % mod;
    }
}
