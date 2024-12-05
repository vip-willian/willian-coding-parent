package cn.willian.coding.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 12:16:54
 */
// https://leetcode.cn/problems/surrounded-regions/description
// 130. 被围绕的区域
// 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
// 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
// 区域：连接所有 'O' 的单元格来形成一个区域。
// 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
// 通过将输入矩阵 board 中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。

// 示例 1：
// 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
// 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

// 示例 2：
// 输入：board = [["X"]]
// 输出：[["X"]]
@SuppressWarnings("all")
public class Code130_SurroundedRegions {

    private static final int[] d_rows = {-1, 1, 0, 0};
    private static final int[] d_cols = {0, 0, -1, 1};

    public static void main(String[] args) {
        char[][] grid = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'O', 'X', 'O', 'X'}, {'O', 'O', 'X', 'X'}};
        solve3(grid);
        System.out.println(Arrays.deepToString(grid));
    }

    // dfs 递归
    public static void solve1(char[][] board) {

        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        // 搜索所有的边，将边上的O替换成 "#"
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean isEdge = i == 0 || j == 0 || i == row - 1 || j == col - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs1(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                // 将为#号的还原成目标0
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // dfs 栈
    public static void solve2(char[][] board) {

        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        // 搜索所有的边，将边上的O替换成 "#"
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean isEdge = i == 0 || j == 0 || i == row - 1 || j == col - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs2(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                // 将为#号的还原成目标0
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // bfs 队列
    public static void solve3(char[][] board) {

        if (null == board || board.length == 0 || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        // 搜索所有的边，将边上的O替换成 "#"
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean isEdge = i == 0 || j == 0 || i == row - 1 || j == col - 1;
                if (isEdge && board[i][j] == 'O') {
                    bfs1(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                // 将为#号的还原成目标0
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void dfs1(char[][] board, int i, int j) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        for (int k = 0; k < 4; k++) {
            dfs1(board, i + d_rows[k], j + d_cols[k]);
        }
    }

    // dfs 深度优先遍历，搜索到某个路径上满足条件，先去优先搜索这个满足条件的上下左右元素，再去搜索本身
    private static void dfs2(char[][] board, int i, int j) {

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {i, j});
        board[i][j] = '#';

        out:
        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int x = current[0];
            int y = current[1];
            // 上下左右 四个方向搜索 为0的替换 先往一个方向使劲去搜索
            for (int k = 0; k < 4; k++) {
                int dx = x + d_rows[k];
                int dy = y + d_cols[k];
                if (dx >= 0 && dx <= board.length - 1 && dy >= 0 && dy <= board[0].length - 1 && board[dx][dy] == 'O') {
                    stack.push(new int[] {dx, dy});
                    board[dx][dy] = '#';
                    continue out;
                }
            }
            stack.pop();
        }
    }

    // bfs 广度优先遍历，先把这个位置的元素全部搜索完成
    private static void bfs1(char[][] board, int i, int j) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        board[i][j] = '#';

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 上下左右 四个方向搜索 为0的替换 按层去搜
            for (int k = 0; k < 4; k++) {
                int dx = x + d_rows[k];
                int dy = y + d_cols[k];
                if (dx >= 0 && dx <= board.length - 1 && dy >= 0 && dy <= board[0].length - 1 && board[dx][dy] == 'O') {
                    queue.add(new int[] {dx, dy});
                    board[dx][dy] = '#';
                }
            }
        }
    }
}
