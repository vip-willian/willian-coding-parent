package cn.willian.coding.leetcode;

/**
 * 1137. 第 N 个泰波那契数
 * <p>
 * https://leetcode.cn/problems/n-th-tribonacci-number/description/?envType=study-plan-v2&envId=dynamic-programming
 * <p>
 * 泰波那契序列 Tn 定义如下：
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4 输出：4
 * <p>
 * 解释： T_3 = 0 + 1 + 1 = 2 T_4 = 1 + 1 + 2 = 4
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 25 输出：1389537
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 37 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 16:02:51
 */
public class TribonacciNumber {

    public static void main(String[] args) {
        System.out.println(tribonacci1(25));
        System.out.println(tribonacci2(25));
    }

    public static int tribonacci1(int n) {

        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return tribonacci1(n - 1) + tribonacci1(n - 2) + tribonacci1(n - 3);
    }

    public static int tribonacci2(int n) {

        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
