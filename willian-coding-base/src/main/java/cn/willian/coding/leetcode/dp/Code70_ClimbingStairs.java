package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-03 17:27:34
 */
// 70. 爬楼梯
// https://leetcode.cn/problems/climbing-stairs/description
public class Code70_ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs1(5));
        System.out.println(climbStairs2(5));
    }

    public static int climbStairs1(int n) {

        return process(n);
    }

    public static int climbStairs2(int n) {

        if(n == 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 到达n位置的方法数
    private static int process(int n) {

        // 1个台阶的话，有1种方法
        if (n == 1) {
            return 1;
        }
        // 2个台阶的话，有2种方法
        if (n == 2) {
            return 2;
        }
        // 其他台阶场景 = 到达n-1台阶后走一步的方法数 + 到达n-2台阶后走2步的方法数
        return process(n - 1) + process(n - 2);
    }
}
