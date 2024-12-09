package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 22:43:17
 */
// https://leetcode.cn/problems/distinct-subsequences/description/
// 115. 不同的子序列
// 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。

// 示例 1：
// 输入：s = "rabbbit", t = "rabbit"
// 输出：3
// 解释：
// 如下所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
// rabbbit
// rabbbit
// rabbbit

// 示例 2：
// 输入：s = "babgbag", t = "bag"
// 输出：5
// 解释：
// 如下所示, 有 5 种可以从 s 中得到 "bag" 的方案。
// babgbag
// babgbag
// babgbag
// babgbag
// babgbag
public class Code115_DistinctSubSequences {

    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        System.out.println(numDistinct1(s, t));
        System.out.println(numDistinct2(s, t));
    }

    public static int numDistinct1(String s, String t) {
        // 取i长度的前缀串生成子序列
        // 能满足t长度的前缀串
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int m = source.length;
        int n = target.length;
        return process(source, target, m, n);
    }

    // 以....字符结尾能拼凑的子序列
    // 以xx前缀长度能拼凑的子序列
    public static int numDistinct2(String s, String t) {
        // 取i长度的前缀串生成子序列
        // 能满足t长度的前缀串
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int m = source.length;
        int n = target.length;
        // source[前缀长度为i]的所有子序列中，有多少个子序列等于target[前缀长度为j]
        int[][] dp = new int[m + 1][n + 1];
        // 第0列全为1
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 子序列不以source[i-1]位置上的字符结尾
                dp[i][j] = dp[i - 1][j];
                // 子序列以source[i-1]位置上的字符结尾，那么必须结尾数字与目标target[j-1]位置上的字符相同
                // i,j为长度含义，i-1就是代表下标，j-1代表下标
                if (source[i - 1] == target[j - 1]) {
                    // 在下标位置相同，从i-2位置下标确认子序列的数量
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    // source[前缀长度为i]的所有子序列中，有多少个子序列等于target[前缀长度为j]
    private static int process(char[] source, char[] target, int i, int j) {

        // 当目标前缀长度为0时，只有一个空串的子序列
        if (j == 0) {
            return 1;
        }
        // j != 0
        // 当source[前缀长度为i]为空，目标不为空时，子序列一个都找不到
        if (i == 0) {
            return 0;
        }
        // 子序列不以source[i-1]位置上的字符结尾
        int ans = process(source, target, i - 1, j);
        // 子序列以source[i-1]位置上的字符结尾，那么必须结尾数字与目标target[j-1]位置上的字符相同
        // i,j为长度含义，i-1就是代表下标，j-1代表下标
        if (source[i - 1] == target[j - 1]) {
            // 在下标位置相同，从i-2位置下标确认子序列的数量
            ans += process(source, target, i - 1, j - 1);
        }
        return ans;
    }
}
