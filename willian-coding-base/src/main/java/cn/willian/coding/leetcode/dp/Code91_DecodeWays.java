package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-06 14:15:13
 */
// https://leetcode.cn/problems/decode-ways/description/
// 91. 解码方法
// 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
// "1" -> 'A'
// "2" -> 'B'
// ...
// "25" -> 'Y'
// "26" -> 'Z'
// 然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。
// 例如，"11106" 可以映射为：
// "AAJF" ，将消息分组为 (1, 1, 10, 6)
// "KJF" ，将消息分组为 (11, 10, 6)
// 消息不能分组为 (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
// 注意，可能存在无法解码的字符串。
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。
// 题目数据保证答案肯定是一个 32 位 的整数。

// 示例 1：
// 输入：s = "12"
// 输出：2
// 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。

// 示例 2：
// 输入：s = "226"
// 输出：3
// 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

// 示例 3：
// 输入：s = "06"
// 输出：0
// 解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
public class Code91_DecodeWays {
    public static void main(String[] args) {

        String s = "27";
        System.out.println(numDecodeWays1(s));
        System.out.println(numDecodeWays2(s));
    }

    public static int numDecodeWays1(String s) {

        char[] chars = s.toCharArray();
        return process(chars, 0);
    }

    public static int numDecodeWays2(String s) {

        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                dp[i] = 0;
            } else {
                // 普遍情况位置
                // 1、自己单独转，后续剩下的字符自己转
                int p1 = dp[i + 1];
                // 2、如果当前等于1,可以加上后面一个字符一块转
                int p2 = 0;
                if (i < n - 1 && chars[i] == '1') {
                    p2 = dp[i + 2];
                }
                // 3、如果当前等于2,且后面一个字符串小于等于6 可以一起转
                int p3 = 0;
                if (i < n - 1 && chars[i] == '2' && chars[i + 1] - '0' <= 6) {
                    p3 = dp[i + 2];
                }
                dp[i] = p1 + p2 + p3;
            }
        }
        return dp[0];
    }

    // 从index位置开始，能拼接出的方法数
    private static int process(char[] chars, int index) {

        // 已经到了最后一个字符，说明找到了一种方法
        int n = chars.length;
        if (index == n) {
            return 1;
        }
        // 如果当前位置字符串为0，能拼接出来的方法数为0
        if (chars[index] == '0') {
            return 0;
        }
        // 普遍情况位置
        // 1、自己单独转，后续剩下的字符自己转
        int p1 = process(chars, index + 1);
        // 2、如果当前等于1,可以加上后面一个字符一块转
        int p2 = 0;
        if (index < n - 1 && chars[index] == '1') {
            p2 = process(chars, index + 2);
        }
        // 3、如果当前等于2,且后面一个字符串小于等于6 可以一起转
        int p3 = 0;
        if (index < n - 1 && chars[index] == '2' && chars[index + 1] - '0' <= 6) {
            p3 = process(chars, index + 2);
        }
        return p1 + p2 + p3;
    }
}
