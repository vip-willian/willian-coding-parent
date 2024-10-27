package cn.willian.coding.leetcode;

/**
 * 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2 输出：2 解释：有两种方法可以爬到楼顶。 1. 1 阶 + 1 阶 2. 2 阶
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3 输出：3 解释：有三种方法可以爬到楼顶。 1. 1 阶 + 1 阶 + 1 阶 2. 1 阶 + 2 阶 3. 2 阶 + 1 阶
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 15:15:22
 */
public class Staircase {

    public static void main(String[] args) {
        System.out.println(climbStairs1(5));
        System.out.println(climbStairs2(5));
    }

    public static int climbStairs1(int n) {

        if (n <= 1) {
            // 代表仅有1个楼梯了，那仅剩1中方法
            return 1;
        }
        if (n == 2) {
            // 代表有2种方法，可以直接到 ([1,1] 2)
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int climbStairs2(int n) {

        return loop(n);
    }

    // 暴力递归
    public static int loop(int n) {

        if (n == 1) {
            // 代表仅有1个楼梯了，那仅剩1中方法
            return 1;
        }
        if (n == 2) {
            // 代表有2种方法，可以直接到 ([1,1] 2)
            return 2;
        }
        // 走1步还剩多少方式可以走 + 走2步还剩多少方式可以走
        return loop(n - 1) + loop(n - 2);
    }
}
