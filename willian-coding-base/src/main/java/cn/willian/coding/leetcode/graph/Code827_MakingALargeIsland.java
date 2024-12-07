package cn.willian.coding.leetcode.graph;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-06 10:23:57
 */
// https://leetcode.cn/problems/making-a-large-island/description/
// 827. 最大人工岛
// 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
// 返回执行此操作后，grid 中最大的岛屿面积是多少？
// 岛屿 由一组上、下、左、右四个方向相连的 1 形成。

// 示例 1:
// 输入: grid = [[1, 0], [0, 1]]
// 输出: 3
// 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。

// 示例 2:
// 输入: grid = [[1, 1], [1, 0]]
// 输出: 4
// 解释: 将一格0变成1，岛屿的面积扩大为 4。

// 示例 3:
// 输入: grid = [[1, 1], [1, 1]]
// 输出: 4
// 解释: 没有0可以让我们变成1，面积依然为 4。
public class Code827_MakingALargeIsland {

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 0, 0}, {0, 1, 1, 1, 1, 0, 0}};
        System.out.println(largestIsland(grid));
    }

    public static int largestIsland(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        // 第一步：将所有岛屿都进行感染
        int id = 2;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, id++);
                }
            }
        }

        int ans = 0;
        // 第二步：计算每个岛的此时大小
        int[] size = new int[Math.max(id, 4)];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] > 1) {
                    ans = Math.max(ans, ++size[grid[i][j]]);
                }
            }
        }
        // 第三步：判断每一个0位置变换后能获得的最大
        boolean[] visited = new boolean[Math.max(id, 4)];
        int total;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    int up = i > 0 ? grid[i - 1][j] : 0;
                    int down = i + 1 < row ? grid[i + 1][j] : 0;
                    int left = j > 0 ? grid[i][j - 1] : 0;
                    int right = j + 1 < col ? grid[i][j + 1] : 0;

                    // 设置上边
                    visited[up] = true;
                    // 将上边位置改为1，获得的面积大小
                    total = 1 + size[up];

                    // 设置下边
                    // 将下边位置改为1，获得的面积大小
                    if (!visited[down]) {
                        total += size[down];
                        visited[down] = true;
                    }

                    // 设置左边
                    // 将左边位置改为1，获得的面积大小
                    if (!visited[left]) {
                        total += size[left];
                        visited[left] = true;
                    }

                    // 设置右边
                    // 将右边位置改为1，获得的面积大小
                    if (!visited[right]) {
                        total += size[right];
                        visited[right] = true;
                    }
                    ans = Math.max(ans, total);

                    visited[up] = false;
                    visited[down] = false;
                    visited[left] = false;
                    visited[right] = false;
                }
            }
        }
        return ans;
    }

    private static void dfs(int[][] grid, int i, int j, int id) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = id;
        // 上
        dfs(grid, i - 1, j, id);
        // 下
        dfs(grid, i + 1, j, id);
        // 左
        dfs(grid, i, j - 1, id);
        // 右
        dfs(grid, i, j + 1, id);
    }

}
