package cn.willian.coding.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-25 16:59:49
 */
// https://leetcode.cn/problems/n-queens/description/
// 51. N 皇后
// 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
// n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
public class NQueens {

    private static final List<List<String>> result = new ArrayList<>();

    public static void main(String[] args) {
        List<List<String>> result = solveNQueens(4);
        System.out.println(result);
    }

    public static List<List<String>> solveNQueens(int n) {

        if (n <= 0) {
            return result;
        }
        result.clear();
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backtracking(chessboard, n, 0);
        return result;
    }

    private static void backtracking(char[][] chessboard, int n, int row) {

        if (row == n) {
            result.add(arryToList(chessboard));
            return;
        }
        // 放每一行的数据
        for (int col = 0; col < n; col++) {
            if (isValid(chessboard, row, col, n)) {
                chessboard[row][col] = 'Q';
                backtracking(chessboard, n, row + 1);
                chessboard[row][col] = '.';
            }
        }
    }

    private static List<String> arryToList(char[][] chessboard) {

        List<String> result = new ArrayList<>(chessboard.length);
        for (char[] chars : chessboard) {
            result.add(String.copyValueOf(chars));
        }
        return result;
    }

    private static boolean isValid(char[][] chessboard, int row, int col, int n) {

        // 检查列有没有Q
        for (int i = 0; i < row; i++) {
            // 遍历每一个行的同一列，是否已经存在Q
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 检查45度角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度角
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
