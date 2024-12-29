package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 10:45:08
 */
// 序号6
// 标题：找单词
// 给一个字符串和一个二维码字符数组，如果该字符串存在于该数组中。则按字符串的字符顺序输出字符串每个字符所在单元格的位置下标字符串，
// 如果找不到返回字符串“N”
// 1.需要按照字符串的字符组成顺序搜索，且搜索到的位置必须是相邻单元格，其中“相邻单元格”是指那些水
// 平相邻或垂直相邻的单元格。
// 2.同一个单元格内的字母不允许被重复使用。
// 3.假定在数组中最多只存在一个可能得匹配。
// 输入描述
//
// 1.第一行为一个数字（N）指示二维数组在后续输入所占的行数。
// 2.第2行到第N+1行输入为一个二维大写字符数组，每行字符用半角,分割。
// 3.第N+2行为待查找的字符串，由大写字符组成。
// 4.二维数组的大小为N*N，
// 0＜N＜＝100。
// 5.单词长度k，0＜k＜1000。
//
// 输出描述：
//
// 输出一个位置下标字符串，拼接格式为：第1个字符行下标+“
// ,
// ”+第1个字符列下标+“
// ,
// ”+第2个字符行下标
// +“
// ,
// ”+第2个字符列下标......+“
// ,
// ”+第N个字符行下标+“
// ,
// ”+第N个字符列下标
//
// 示例1：
// 输入
// 4
// A,C,C,F
// C,D,E,D
// B,E,S,S
// F,E,C,A
// ACCESS
//
// 输出
// 0,0,0,1,0,2,1,2,2,2,2,3
public class FindWord {

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 二维数组大小
        int n = sc.nextInt();
        sc.nextLine();
        // 定义二维数组
        char[][] g = new char[n][n];
        // 读取n行二维数组数据
        for (int i = 0; i < n; i++) {
            String t = sc.nextLine().trim();
            for (int j = 0; j < n; j++) {
                g[i][j] = t.charAt(j * 2);
            }
        }
        // 目标单词
        String word = sc.nextLine().trim();
        // 查询目标单词
        findWord(n, g, word);
    }

    public static void findWord(int n, char[][] g, String word) {

        // 记录访问过的二维数组的下标位置
        boolean[][] visited;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 如果该位置上的字符等于目标位置的第一个字符，可以进行搜索
                if (g[i][j] == word.charAt(0)) {
                    visited = new boolean[n][n];
                    List<Pair> path = new ArrayList<>();
                    // 添加第一个找到的位置
                    path.add(new Pair(i, j));
                    // 深度优先搜索,满足条件就停止外层循环
                    if (dfs(g, i, j, word, 0, path, visited)) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean dfs(char[][] g, int x, int y, String word, int cnt, List<Pair> path, boolean[][] visited) {

        // 判断是否已经越界 或者 是否已经访问过 或者 该位置上的字符串不匹配
        if (x < 0 || x >= g.length || y < 0 || y >= g[0].length || visited[x][y] || g[x][y] != word.charAt(cnt)) {
            return false;
        }
        // 如果已经找到了字符串的最后一个字符，输出路径，并返回true
        if (cnt == word.length() - 1) {
            printPath(path);
            return true;
        }
        // 标记当前位置已经被访问
        visited[x][y] = true;
        // 遍历上下左右4个方向进行寻找
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            // 记录路径
            path.add(new Pair(a, b));
            if (dfs(g, a, b, word, cnt + 1, path, visited)) {
                return true;
            }
            // 回溯，移除路径，查找下一个方向
            path.remove(path.size() - 1);
        }
        // 恢复当前位置未访问状态
        visited[x][y] = false;
        return false;
    }

    private static void printPath(List<Pair> path) {
        for (int i = 0; i < path.size(); i++) {
            Pair pair = path.get(i);
            System.out.print(pair.a + "," + pair.b);
            if (i < path.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
