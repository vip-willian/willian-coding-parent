package cn.willian.coding.leetcode.tree;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 23:06:10
 */
public class PaperFolding {

    public static void main(String[] args) {
        int N = 3;
        printAllFold(N);
    }

    private static void printAllFold(int N) {

        printLevelFold(1, N, true);
    }

    // level 当前对折层数
    // N 总对折层数
    // down 产生折痕方向 true -> 凹 false -> 凸
    private static void printLevelFold(int level, int N, boolean down) {

        if (level > N) {
            return;
        }
        printLevelFold(level + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printLevelFold(level + 1, N, false);
    }
}
