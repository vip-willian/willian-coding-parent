package cn.willian.coding.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 20:48:54
 */
// https://leetcode.cn/problems/number-of-islands/description/
public class Code200_NumberOfIsLands {

    private static final int[] d_rows = new int[] {-1, 1, 0, 0};
    private static final int[] d_cols = new int[] {0, 0, -1, 1};

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}};
        System.out.println(numIslands1(grid));
        System.out.println(numIslands2(grid));
    }

    // DFS解法
    public static int numIslands1(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    // 第一次找到为1
                    count++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j, int rows, int cols) {

        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int k = 0; k < 4; k++) {
            dfs(grid, i + d_rows[k], j + d_cols[k], rows, cols);
        }
    }

    // BFS解法
    public static int numIslands2(char[][] grid) {
        // 从每个顶点为1的点开始BFS 遍历过程中的点置为0 最终BFS的次数就是岛屿数量
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, rows, cols, i, j);
                }
            }
        }
        return count;
    }

    private static void bfs(char[][] grid, int rows, int cols, int i, int j) {

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0')
            return;
        Queue<int[]> queue = new LinkedList<>();
        grid[i][j] = '0';
        queue.add(new int[] {i, j});
        while (!queue.isEmpty()) {
            int[] index = queue.poll();
            int x = index[0];
            int y = index[1];
            for (int k = 0; k < 4; k++) {
                int nx = x + d_rows[k];
                int ny = y + d_cols[k];
                if (nx < 0 || nx >= rows || ny < 0 || ny >= cols)
                    continue;
                if (grid[nx][ny] == '1') {
                    grid[nx][ny] = '0';
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
