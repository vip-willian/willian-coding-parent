package cn.willian.coding.leetcode;

/**
 * 509. 斐波那契数
 * <p>
 * https://leetcode.cn/problems/fibonacci-number/description/?envType=study-plan-v2&envId=dynamic-programming
 * <p>
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1 F(n) = F(n - 1) + F(n - 2)，其中 n > 1 给定 n ，请计算 F(n) 。
 * <p>
 *
 * 示例 1：
 * <p>
 * 输入：n = 2 输出：1 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3 输出：2 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * <p>
 * 示例 3：
 * <p>
 * 输入：n = 4 输出：3 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 30
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 15:51:50
 */
public class FibonacciNumber {

    public static void main(String[] args) {
        fib1(4);
        fib2(4);
    }

    public static int fib2(int n) {

        if (0 == n) {
            return 0;
        }
        if (1 == n) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        if (n >= 2) {
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    public static int fib1(int n) {

        if (0 == n) {
            return 0;
        }
        if (1 == n) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }
}
