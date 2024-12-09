package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-07 23:23:47
 */
// https://leetcode.cn/problems/edit-distance/description/
// 72. 编辑距离
// 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
// 你可以对一个单词进行如下三种操作：
//
// 插入一个字符
// 删除一个字符
// 替换一个字符

// 示例 1：
// 输入：word1 = "horse", word2 = "ros"
// 输出：3
// 解释：
// horse -> rorse (将 'h' 替换为 'r')
// rorse -> rose (删除 'r')
// rose -> ros (删除 'e')

// 示例 2：
// 输入：word1 = "intention", word2 = "execution"
// 输出：5
// 解释：
// intention -> inention (删除 't')
// inention -> enention (将 'i' 替换为 'e')
// enention -> exention (将 'n' 替换为 'x')
// exention -> exection (将 'n' 替换为 'c')
// exection -> execution (插入 'u')
public class Code72_EditDistance {

    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";
        System.out.println(minDistance1(word1, word2));
        System.out.println(minDistance2(word1, word2));
    }

    public static int minDistance1(String word1, String word2) {
        // word1 取前i个字符 能满足 word2取前j个字符 的 最小距离
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();

        int m = word1Array.length, n = word2Array.length;
        return process(word1Array, word2Array, m, n, 1, 1, 1);
    }

    public static int minDistance2(String word1, String word2) {
        // word1 取前i个字符 能满足 word2取前j个字符 的 最小距离
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();

        int m = word1Array.length, n = word2Array.length;
        int insert = 1, delete = 1, replace = 1;

        int[][] dp = new int[m + 1][n + 1];

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j * insert;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * delete;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 第一种可能性：word1Array[0...len1-1]位置 参与 word2Array[0...len2-1]位置 的变换
                dp[i][j] = Integer.MAX_VALUE;
                // 1.1、参与的情况下，参与变成len2 - 1位置的字符串 且 两边字符串最后一个字符相等
                if (word1Array[i - 1] == word2Array[j - 1]) {
                    // 那么只要获取他们前面字符串能匹配的最小距离
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else {
                    // 1.2、参与的情况下，参与变成len2 - 1位置的字符串 且 两边字符串最后一个字符不相等,说明最后的字符串要替换
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + replace);
                }
                // 1.3、参与的情况下，但不参与变成len2 - 1位置的字符串，参与变成len2-2位置，len2 - 1由后续插入满足
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + insert);

                // 第二种可能性：word1Array[0...len1-1]位置 不参与 word2Array[0...len2-1]位置
                // 的变换，需要付出删除的代价，然后由word1Array[0...len1-2]位置去满足
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + delete);
            }
        }
        return dp[m][n];
    }

    /**
     * @param word1Array 单词1
     * @param word2Array 单词2
     * @param len1 word1 取前len1个字符
     * @param len2 word2取前len2个字符
     * @param insert 插入代价
     * @param delete 删除代价
     * @param replace 替换代价
     * @return word1 取前len1个字符 能满足 word2取前len2个字符 的 最小距离
     */
    private static int process(char[] word1Array, char[] word2Array, int len1, int len2, int insert, int delete,
        int replace) {

        // 2个长度都为0，变换代价为0
        if (len1 == 0 && len2 == 0) {
            return 0;
        }
        // len1 = 0 len2 != 0，原字符一个都没有，要变成新字符，新字符有多少长度进行插入
        if (len1 == 0) {
            return word2Array.length * insert;
        }
        // len1 != 0 len2=0，原字符存在，新字符为空，原字符有多少长度进行删除
        if (len2 == 0) {
            return word1Array.length * delete;
        }
        // 第一种可能性：word1Array[0...len1-1]位置 参与 word2Array[0...len2-1]位置 的变换
        int minDistance = Integer.MAX_VALUE;
        // 1.1、参与的情况下，参与变成len2 - 1位置的字符串 且 两边字符串最后一个字符相等
        if (word1Array[len1 - 1] == word2Array[len2 - 1]) {
            // 那么只要获取他们前面字符串能匹配的最小距离
            minDistance =
                Math.min(minDistance, process(word1Array, word2Array, len1 - 1, len2 - 1, insert, delete, replace));
        } else {
            // 1.2、参与的情况下，参与变成len2 - 1位置的字符串 且 两边字符串最后一个字符不相等,说明最后的字符串要替换
            minDistance = Math.min(minDistance,
                process(word1Array, word2Array, len1 - 1, len2 - 1, insert, delete, replace) + replace);
        }
        // 1.3、参与的情况下，但不参与变成len2 - 1位置的字符串，参与变成len2-2位置，len2 - 1由后续插入满足
        minDistance =
            Math.min(minDistance, process(word1Array, word2Array, len1, len2 - 1, insert, delete, replace) + insert);

        // 第二种可能性：word1Array[0...len1-1]位置 不参与 word2Array[0...len2-1]位置 的变换，需要付出删除的代价，然后由word1Array[0...len1-2]位置去满足
        minDistance =
            Math.min(minDistance, process(word1Array, word2Array, len1 - 1, len2, insert, delete, replace) + delete);
        return minDistance;
    }
}
