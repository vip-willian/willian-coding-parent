package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 16:25:21
 */
// https://leetcode.cn/problems/word-search/description/
// 79. 单词搜索
// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

// 示例 1：
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// 输出：true

// 示例 2：
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
// 输出：true

// 示例 3：
// 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
// 输出：false

// 提示：
//
// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board 和 word 仅由大小写英文字母组成
public class Code79_WordSearch {

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {

        // 尝试从每一个开始去进行单词的匹配
        int m = board.length;
        int n = board[0].length;

        char[] words = word.toCharArray();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isExists(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 判断（i，j）位置的字符 是否搞定 words[k] 的字符串
    private static boolean isExists(char[][] board, char[] words, int i, int j, int k) {

        // base case
        if (k == words.length) {
            // 已经到了单词的最后，说明前面都已经搞定
            return true;
        }
        // 搞不定的场景:边界越界 以及 board[i][j]位置上的字符与 words[k]位置上的字符不一致
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != words[k]) {
            return false;
        }
        // 走到这说明当前位置已经能搞定
        // 由于走过的路不能够再走，先将走过的路替换成一个新的值
        char temp = board[i][j];
        board[i][j] = '0';
        // 向上下左右四个方向位置搜索，是否能搞定下一个位置
        boolean ans = isExists(board, words, i - 1, j, k + 1) || isExists(board, words, i + 1, j, k + 1)
            || isExists(board, words, i, j - 1, k + 1) || isExists(board, words, i, j + 1, k + 1);
        board[i][j] = temp;
        return ans;
    }
}
