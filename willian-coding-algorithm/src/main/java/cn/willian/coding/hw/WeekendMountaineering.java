package cn.willian.coding.hw;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-31 15:39:33
 */
// 题目描述
// 周末小明准备去爬山锻炼，0代表平地，山的高度使用1到9来表示，小明每次爬山或下山高度只能相差k及k以内，每次只能上下左右一个方向上移动一格，小明从左上角(0,0)位置出发
//
// 输入描述
// 第一行输入m n k(空格分隔)。代表m*n的二维山地图，k为小明每次爬山或下山高度差的最大值
// 然后接下来输入山地图，一共m行n列，均以空格分隔。取值范围:
// 0< m <= 500
// 0<n<=500
// 0<k<5
//
// 输出描述
// 请问小明能爬到的最高峰多高，到该最高峰的最短步数，输出以空格分隔。
// 同高度的山峰输出较短步数.，如果没有可以爬的山峰，则高度和步数都返回0
//
// 示例1：
//
// 输入
// 5 4 1
// 0 1 2 0
// 1 0 0 0
// 1 0 1 2
// 1 3 1 0
// 0 0 0 9
//
// 输出
// 2 2
//
// 说明
// 根据山地图可知，能爬到的最高峰在(0,2)位置，高度为2，最短路径为(0,0)-(0,1)-(0,2)，最短步数为2。
//
// 示例2：
//
// 输入
// 5 4 3
// 0 0 0 0
// 0 0 0 0
// 0 9 0 0
// 0 0 0 0
// 0 0 0 9
//
// 输出
// 0 0
// 说明
// 根据山地图可知，每次爬山距离3，无法爬到山峰上，步数为0.
public class WeekendMountaineering {
    private final static int[] dx = {-1, 1, 0, 0};
    private final static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[][] grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            int[] result = getResult(grid, m, n, k);
            System.out.println(result[0] + " " + result[1]);
        }
    }

    private static int[] getResult(int[][] grid, int m, int n, int k) {

        // 初始化一个哈希表，用于存储到达不同高度的最短步数
        Map<Integer, Integer> minStepToHeight = new HashMap<>();
        minStepToHeight.put(grid[0][0], 0);

        // 初始化一个记忆化数组，用于记录已经访问过的位置和步数
        int[][] memo = new int[m][n];
        // 初始化一个布尔数组，用于记录已经访问过的位置
        boolean[][] visited = new boolean[m][n];

        // 深度优先搜索
        dfs(0, 0, 0, minStepToHeight, grid, k, memo, visited);

        // 计算最高峰的高度和最短步数
        int maxHeight = minStepToHeight.keySet().stream().max(Comparator.comparingInt(a -> a)).orElse(0);
        int minStep = minStepToHeight.get(maxHeight);

        return new int[] {maxHeight, minStep};
    }

    private static void dfs(int x, int y, int step, Map<Integer, Integer> minStepToHeight, int[][] grid, int k,
        int[][] memo, boolean[][] visited) {

        // 获取当前位置高度
        int lastHeight = grid[x][y];

        // 遍历4个方向
        for (int i = 0; i < 4; i++) {
            // 计算新的位置
            int newX = x + dx[i];
            int newY = y + dy[i];

            // 检查新位置是否在矩阵范围内
            if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length) {
                continue;
            }
            // 获取新位置的高度
            int curHeight = grid[newX][newY];

            // 检查两个位置的高度差是否在K以内
            if (Math.abs(curHeight - lastHeight) <= k) {
                // 增加步数
                step++;
                // 更新到达新高度的最短步数
                if (!minStepToHeight.containsKey(curHeight) || minStepToHeight.get(curHeight) > step) {
                    minStepToHeight.put(curHeight, step);
                }
                // 检查记忆化数组，避免重复计算
                if (memo[newX][newY] == 0 || memo[newX][newY] > step) {
                    // 更新记忆化数组
                    memo[newX][newY] = step;
                    // 标记当前位置已经访问
                    visited[x][y] = true;
                    // 递归调用深度优先搜索
                    dfs(newX, newY, step, minStepToHeight, grid, k, memo, visited);
                    // 回溯时，将当前位置标记为未访问
                    visited[x][y] = false;
                }
                // 减少步数
                step--;
            }
        }
    }
}
