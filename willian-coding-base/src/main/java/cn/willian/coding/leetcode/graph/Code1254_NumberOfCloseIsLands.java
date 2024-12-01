package cn.willian.coding.leetcode.graph;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 20:48:54
 */
// https://leetcode.cn/problems/number-of-closed-islands/description/
public class Code1254_NumberOfCloseIsLands {

    private static final int[] d_rows = new int[] {-1, 1, 0, 0};
    private static final int[] d_cols = new int[] {0, 0, -1, 1};
    private static int var = 0;

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
        System.out.println(closedIsland(grid));
    }

    public static int closedIsland(int[][] grid) {

        if (grid == null || grid.length < 3 || grid[0].length < 3) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    // 从该点出发 先假定能找个一个封闭岛屿
                    // 如果在DFS过程中遇到了边界 则置为0 即从该点i,j出发不是封闭岛屿
                    var = 1;
                    dfsClosedIsland(grid, i, j, rows, cols);
                    count += var;
                }
            }
        }
        return count;
    }

    private static void dfsClosedIsland(int[][] grid, int i, int j, int rows, int cols) {
        // 到了边界 直接返回0 必定不是封闭岛屿
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            var = 0;
            return;
        }
        // 如果当前位置为1 直接返回 继续DFS
        if (grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;
        // 向上下左右四个方向继续DFS
        for (int k = 0; k < 4; k++) {
            dfsClosedIsland(grid, i + d_rows[k], j + d_cols[k], rows, cols);
        }
    }
}
