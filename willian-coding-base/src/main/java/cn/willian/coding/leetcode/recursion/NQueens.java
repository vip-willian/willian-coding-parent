package cn.willian.coding.leetcode.recursion;

import cn.willian.coding.tools.Prints;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-02 10:33:36
 */
public class NQueens {

    public static void main(String[] args) {

        int n = 4;
        int[] records = new int[n];
        // System.out.println(process1(0, records, n));
        System.out.println(process2(n));

    }

    // row 表示在 第i行放置皇后
    // records 存放皇后位置的列
    // n 棋盘大小 n * n
    private static int process1(int row, int[] records, int n) {

        // 已经走到最后一行,说明前面选择的都有效
        if (row == n) {
            return 1;
        }
        // 在棋盘某一个列选择放置皇后
        int res = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, records)) {
                // 记录放置皇后位置的列
                records[row] = col;
                // 如果row行选出有效的结果时，去下一行再去做决策
                res += process1(row + 1, records, n);
            }
        }
        return res;
    }

    private static int process2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        Prints.printBinaryStr("棋盘限制:", limit);
        return process2(limit, 0, 0, 0);
    }

    // colLimit 列限制
    // leftDiaLimit 左斜限制
    // rightDiaLimit 右斜限制
    private static int process2(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {

        System.out.println("--------------------------------------------------");
        // 已经走到最后一行,说明前面选择的都有效
        if (colLimit == limit) {
            return 1;
        }
        // 在棋盘某一个列选择放置皇后
        int res = 0;
        int x1 = colLimit | leftDiaLimit | rightDiaLimit;
        Prints.printBinaryStr("colLimit | leftDiaLimit | rightDiaLimit = x1:", x1);
        int x2 = ~x1;
        Prints.printBinaryStr("~x1 = x2:", x2);
        int pos = limit & x2;
        Prints.printBinaryStr(" limit & x2 = pos位置:", pos);
        int mostRightOne;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            Prints.printBinaryStr("mostRightOne:", mostRightOne);
            pos = pos - mostRightOne;
            Prints.printBinaryStr("pos - mostRightOne = new pos:", pos);

            int newColLimit = colLimit | mostRightOne;
            Prints.printBinaryStr("colLimit | mostRightOne = newColLimit:", newColLimit);

            int tempLeftDiaLimit = leftDiaLimit | mostRightOne;
            Prints.printBinaryStr("leftDiaLimit | mostRightOne = tempLeftDiaLimit:", tempLeftDiaLimit);
            int newLeftDiaLimit = tempLeftDiaLimit << 1;
            Prints.printBinaryStr("tempLeftDiaLimit << 1 = newLeftDiaLimit:", newLeftDiaLimit);

            int tempRightDiaLimit = rightDiaLimit | mostRightOne;
            Prints.printBinaryStr("rightDiaLimit | mostRightOne = tempRightDiaLimit:", tempRightDiaLimit);
            int newRightDiaLimit = tempRightDiaLimit >>> 1;
            Prints.printBinaryStr("tempRightDiaLimit >>> 1 = newRightDiaLimit:", newRightDiaLimit);
            res += process2(limit, newColLimit, newLeftDiaLimit, newRightDiaLimit);
        }
        return res;
    }

    private static boolean isValid(int row, int col, int[] records) {
        // 不能共列 且 不能共斜线
        // 前面已经抉择出来的行去判断
        for (int i = 0; i < row; i++) {
            if (
            // 共列判断
            records[i] == col ||
            // 共斜线判断
                Math.abs(row - i) == Math.abs(col - records[i])) {
                return false;
            }
        }
        return true;
    }

}
