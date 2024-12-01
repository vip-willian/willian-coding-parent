package cn.willian.coding.leetcode.graph;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 20:48:54
 */
// https://leetcode.cn/problems/max-area-of-island/description/
public class Code695_MaxAreaOfIsLands {

    private static final int[] d_rows = new int[] {-1, 1, 0, 0};
    private static final int[] d_cols = new int[] {0, 0, -1, 1};

    public static void main(String[] args) {
        char[][] grid = {{'0', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '0'},
            {'0', '1', '1', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '1', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0'},
            {'0', '1', '0', '0', '1', '1', '0', '0', '1', '1', '1', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0'}};
        System.out.println(maxAreaOfIsland(grid));
    }

    // DFS解法
    public static int maxAreaOfIsland(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    maxArea = Math.max(maxArea, dfsMaxArea(grid, i, j, rows, cols));
                }
            }
        }
        return maxArea;
    }

    private static int dfsMaxArea(char[][] grid, int i, int j, int rows, int cols) {

        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
            return 0;
        }
        grid[i][j] = '0';
        int area = 1;
        for (int k = 0; k < 4; k++) {
            area += dfsMaxArea(grid, i + d_rows[k], j + d_cols[k], rows, cols);
        }
        return area;
    }
}
