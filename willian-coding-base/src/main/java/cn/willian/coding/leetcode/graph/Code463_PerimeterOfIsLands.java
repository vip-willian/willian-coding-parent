package cn.willian.coding.leetcode.graph;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 20:48:54
 */
// https://leetcode.cn/problems/island-perimeter/description/
public class Code463_PerimeterOfIsLands {

    private static final int[] d_rows = new int[] {-1, 1, 0, 0};
    private static final int[] d_cols = new int[] {0, 0, -1, 1};

    public static void main(String[] args) {
        char[][] grid = {{'0', '1', '0', '0'}, {'1', '1', '1', '0'}, {'0', '1', '0', '0'}, {'1', '1', '0', '0'}};
        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    perimeter += dfsPerimeter(grid, i, j, rows, cols);
                }
            }
        }
        return perimeter;
    }

    private static int dfsPerimeter(char[][] grid, int i, int j, int rows, int cols) {

        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
            return 1;
        }
        if (grid[i][j] == '2') {
            return 0;
        }
        grid[i][j] = '2';
        int perimeter = 0;
        for (int k = 0; k < 4; k++) {
            perimeter += dfsPerimeter(grid, i + d_rows[k], j + d_cols[k], rows, cols);
        }
        return perimeter;
    }
}
