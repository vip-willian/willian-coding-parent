package cn.willian.coding.leetcode.graph;

import cn.willian.coding.tools.Prints;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 21:42:24
 */
// https://leetcode.cn/problems/diagonal-traverse/description/
// 498. 对角线遍历
// 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。

// 示例 1：
// 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
// 输出：[1,2,4,7,5,3,6,8,9]

// 示例 2：
// 输入：mat = [[1,2],[3,4]]
// 输出：[1,2,3,4]
public class Code498_DiagonalTraverse {

    private static Integer index = 0;

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] result = findDiagonalOrder(matrix);
        Prints.printArray(result);
    }

    public static int[] findDiagonalOrder(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        // 从左往右的点
        int leftRow = 0;
        int leftCol = 0;
        // 从上往下的点
        int downRow = 0;
        int downCol = 0;

        boolean direct = false;
        int[] result = new int[m * n];
        while (leftRow != m) {
            collectElement(mat, direct, leftRow, leftCol, downRow, downCol, result);
            // 到了最后一列 说明要开始换方向了
            // 从左往右方向 没到最后一列，行不变
            leftRow = leftCol != n - 1 ? leftRow : leftRow + 1;
            // 从左往右方向 到了最后一列，列不变
            leftCol = leftCol == n - 1 ? leftCol : leftCol + 1;

            // 从上往下方向 没到最后一行，列不变
            downCol = downRow != m - 1 ? downCol : downCol + 1;
            // 到了最后一行 说明要开始换方向了
            // 从上往下方向 到最后一行，行不变
            downRow = downRow == m - 1 ? downRow : downRow + 1;

            // 每移动一次换个方向
            direct = !direct;
        }
        return result;
    }

    private static void collectElement(int[][] mat, boolean direct, int leftRow, int leftCol, int downRow, int downCol,
        int[] result) {

        if (direct) {
            while (leftRow != downRow + 1) {
                result[index++] = mat[leftRow++][leftCol--];
            }
        } else {
            while (downRow != leftRow - 1) {
                result[index++] = mat[downRow--][downCol++];
            }
        }
    }
}
