package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 14:32:50
 */
// https://leetcode.cn/problems/unique-substrings-in-wraparound-string/description/
// 467. 环绕字符串中唯一的子字符串
// 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串，所以 base 看起来是这样的：
// "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
// 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现。

// 示例 1：
// 输入：s = "a"
// 输出：1
// 解释：字符串 s 的子字符串 "a" 在 base 中出现。

// 示例 2：
// 输入：s = "cac"
// 输出：2
// 解释：字符串 s 有两个子字符串 ("a", "c") 在 base 中出现。

// 示例 3：
// 输入：s = "zab"
// 输出：6
// 解释：字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
public class Code467_UniqueSubStringInWrapRoundString {
    public static void main(String[] args) {
        String s = "zpabczabefcdmn";
        System.out.println(findSubstringInWrapRoundString(s));
    }

    public static int findSubstringInWrapRoundString(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // 第一步：将字符串改成int类型数组
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - 'a';
        }
        // 第二步：准备一个dp表，dp[i]的含义代表，以i位置字符结尾向左能延伸的最大长度
        int[] dp = new int[26];
        // s的第一个字母永远只能延伸1个长度，就是本身
        dp[nums[0]] = 1;
        for (int i = 1, len = 1; i < n; i++) {
            int cur = nums[i];
            int pre = nums[i - 1];
            // 上个位置是z当前位置是a
            if ((pre == 25 && cur == 0) || (pre + 1 == cur)) {
                len++;
            } else {
                len = 1;
            }
            dp[cur] = Math.max(len, dp[cur]);
        }
        // 第三步：计算dp表的总和
        int ans = 0;
        for (int j : dp) {
            ans += j;
        }
        return ans;
    }
}
