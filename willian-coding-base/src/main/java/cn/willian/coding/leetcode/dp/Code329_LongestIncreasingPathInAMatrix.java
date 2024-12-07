package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 22:11:26
 */
// https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/
// 329. 矩阵中的最长递增路径
// 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。

// 示例 1：
// 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
// 输出：4
// 解释：最长递增路径为 [1, 2, 6, 9]。

// 示例 2：
// 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
// 输出：4
// 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。

// 示例 3：
// 输入：matrix = [[1]]
// 输出：1
public class Code329_LongestIncreasingPathInAMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath(matrix));
    }

    public static int longestIncreasingPath(int[][] matrix) {

        // 从[i,j]位置触发，能够走到的最长递增路径长度
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, process(matrix, i, j, dp));
            }
        }
        return ans;
    }

    private static int process(int[][] matrix, int i, int j, int[][] dp) {

        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 如果上方的值大于我位置上的值
        int max = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            max = Math.max(max, process(matrix, i - 1, j, dp));
        }
        // 如果下方的值大于我位置上的值
        if (i + 1 <= m - 1 && matrix[i + 1][j] > matrix[i][j]) {
            max = Math.max(max, process(matrix, i + 1, j, dp));
        }
        // 如果左方的值大于我位置上的值
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            max = Math.max(max, process(matrix, i, j - 1, dp));
        }
        // 如果右方的值大于我位置上的值
        if (j + 1 <= n - 1 && matrix[i][j + 1] > matrix[i][j]) {
            max = Math.max(max, process(matrix, i, j + 1, dp));
        }
        dp[i][j] = max + 1;
        return max + 1;
    }
}
