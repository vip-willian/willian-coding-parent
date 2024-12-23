package cn.willian.coding.leetcode.graph;

import cn.willian.coding.tools.Prints;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-06 11:21:29
 */
// https://leetcode.cn/problems/bricks-falling-when-hit/
// 803. 打砖块
// 有一个 m x n 的二元网格 grid ，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
// 一块砖直接连接到网格的顶部，或者
// 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
// 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而 掉落 。一旦砖块掉落，它会 立即
// 从网格 grid 中消失（即，它不会落在其他稳定的砖块上）。
// 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
// 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。

// 示例 1：
// 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
// 输出：[2]
// 解释：网格开始为：
// [[1,0,0,0]，
// [1,1,1,0]]
// 消除 (1,0) 处加粗的砖块，得到网格：
// [[1,0,0,0]
// [0,1,1,0]]
// 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
// [[1,0,0,0],
// [0,0,0,0]]
// 因此，结果为 [2] 。

// 示例 2：
// 输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
// 输出：[0,0]
// 解释：网格开始为：
// [[1,0,0,0],
// [1,1,0,0]]
// 消除 (1,1) 处加粗的砖块，得到网格：
// [[1,0,0,0],
// [1,0,0,0]]
// 剩下的砖都很稳定，所以不会掉落。网格保持不变：
// [[1,0,0,0],
// [1,0,0,0]]
// 接下来消除 (1,0) 处加粗的砖块，得到网格：
// [[1,0,0,0],
// [0,0,0,0]]
// 剩下的砖块仍然是稳定的，所以不会有砖块掉落。
// 因此，结果为 [0,0] 。
public class Code803_BricksFallingWhenHit {

    private static int[][] grid;
    private static int row;
    private static int col;

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = {{1, 0}};
        int[] ans = hitBricks(grid, hits);
        Prints.printArray(ans);
    }

    public static int[] hitBricks(int[][] g, int[][] hits) {

        grid = g;
        row = grid.length;
        col = grid[0].length;
        int[] ans = new int[hits.length];

        // 只有天花板，不会掉落砖块
        if (row == 1) {
            return ans;
        }
        // 第一步：炮弹位置值-1
        for (int[] hit : hits) {
            int x = hit[0];
            int y = hit[1];
            grid[x][y]--;
        }
        // 第二步：天花板位置填充为2
        for (int i = 0; i < col; i++) {
            dfs(0, i);
        }
        // 第三步：时光倒流位置炮弹检查
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];
            grid[x][y]++;
            if (worth(x, y)) {
                ans[i] = dfs(x, y) - 1;
            }
        }
        return ans;
    }

    private static boolean worth(int x, int y) {
        // 代表打中了1砖块
        return grid[x][y] == 1 && (
            // 打中了天花板
            x == 0
            // 上边位置与天花板关联
            || (grid[x - 1][y] == 2)
            // 下边位置与天花板关联
            || (x < row -1 && grid[x + 1][y] == 2)
            // 左边位置与天花板关联
            || (y > 0 && grid[x][y - 1] == 2)
            // 右边位置与天花板关联
            || (y < col - 1 && grid[x][y + 1] == 2));
    }

    // 从（i,j）列出发，感染成2
    // 返回统计最终新增了多少个2
    private static int dfs(int i, int j) {

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        return 1 + dfs(i - 1, j) + dfs(i + 1, j) + dfs(i, j - 1) + dfs(i, j + 1);
    }
}
