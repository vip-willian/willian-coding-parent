package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 15:15:16
 */
// https://leetcode.cn/problems/0i0mDW/
// 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：一个机器人每次只能向下或者向右移动一步。

// 示例 1：
// 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
// 输出：7
// 解释：因为路径 1→3→1→1→1 的总和最小。

// 示例 2：
// 输入：grid = [[1,2,3],[4,5,6]]
// 输出：12
public class Code99_MinPathSum {

    public static void main(String[] args) {

        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum1(grid));
        System.out.println(minPathSum2(grid));
        System.out.println(minPathSum3(grid));
    }

    public static int minPathSum1(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        return process(grid, m - 1, n - 1);
    }

    public static int minPathSum2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j] 代表 从(0，0)位置到(i,j)位置，最短路径和
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // 第0行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 第0列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 其他普遍位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 上边 、左边位置最小值
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    // 空间压缩技巧
    public static int minPathSum3(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];
        // 遍历m-1次进行赋值
        for (int i = 0; i < m; i++) {
            // 设置每一列的值
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    // 第一行位置，只依赖左边位置
                    dp[j] = grid[i][j] + (j == 0 ? 0 : dp[j - 1]);
                } else {
                    // 非第一行位置，依赖左边和上边位置最小值
                    // dp[j]: 上边位置
                    // dp[j-1]: 左边位置
                    dp[j] = grid[i][j] + (j == 0 ? dp[j] : Math.min(dp[j - 1], dp[j]));
                }
            }
        }
        return dp[n - 1];
    }

    // 从(0，0)位置到(i,j)位置，最短路径和
    private static int process(int[][] grid, int i, int j) {

        // (i,j)刚好在（0,0）的位置，获得到的最短路径和
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        // 从上边来
        int up = Integer.MAX_VALUE;
        if (i > 0) {
            up = grid[i][j] + process(grid, i - 1, j);
        }
        // 从左边来
        int left = Integer.MAX_VALUE;;
        if (j > 0) {
            left = grid[i][j] + process(grid, i, j - 1);
        }
        return Math.min(left, up);
    }
}
