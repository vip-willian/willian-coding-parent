package cn.willian.coding.leetcode.dp;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-02 09:24:57
 */
public class Hanoi {

    public static void main(String[] args) {
        hanoi(3);
    }

    public static void hanoi(int n) {
        if (n <= 0) {
            return;
        }
        hanoi(n, "左", "右", "中");
    }

    public static void hanoi(int i, String start, String end, String other) {

        if (i == 1) {
            System.out.println("Move " + i + " from " + start + " to " + end);
            return;
        }
        // 将i-1位置的盘从左移动到中
        hanoi(i - 1, start, other, end);
        // 将i位置的盘从左移动到右
        System.out.println("Move " + i + " from " + start + " to " + end);
        // 将i-1位置的盘从中移动到右
        hanoi(i - 1, other, end, start);
    }
}
