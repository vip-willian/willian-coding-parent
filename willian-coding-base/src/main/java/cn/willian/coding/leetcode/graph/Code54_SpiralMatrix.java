package cn.willian.coding.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 21:42:24
 */
// https://leetcode.cn/problems/spiral-matrix/description/
// 54. 螺旋矩阵
// 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

// 示例1
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
// 输出：[1,2,3,6,9,8,7,4,5]

// 示例 2
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
public class Code54_SpiralMatrix {

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        result.clear();

        int leftTopRow = 0;
        int leftTopCol = 0;
        int rightTopRow = matrix.length - 1;
        int rightTopCol = matrix[0].length - 1;

        while (leftTopRow <= rightTopRow && leftTopCol <= rightTopCol) {
            getSpiralOrder(matrix, leftTopRow++, leftTopCol++, rightTopRow--, rightTopCol--, result);
        }
        return result;
    }

    public static void getSpiralOrder(int[][] matrix, int leftTopRow, int leftTopCol, int rightBottomRow,
        int rightBottomCol, List<Integer> result) {

        // 第一种情况 leftTopRow = rightBottomRow
        if (leftTopRow == rightBottomRow) {
            for (int i = leftTopCol; i <= rightBottomCol; i++) {
                result.add(matrix[leftTopRow][i]);
            }
        }
        // 第二种情况 leftTopCol = rightBottomCol
        else if (leftTopCol == rightBottomCol) {
            for (int i = leftTopRow; i <= rightBottomRow; i++) {
                result.add(matrix[i][leftTopCol]);
            }
        } else {
            // 其他通用情况
            int currentRow = leftTopRow;
            int currentCol = leftTopCol;
            // 从左往右
            while (currentCol != rightBottomCol) {
                result.add(matrix[leftTopRow][currentCol]);
                currentCol++;
            }
            // 从上往下
            while (currentRow != rightBottomRow) {
                result.add(matrix[currentRow][rightBottomCol]);
                currentRow++;
            }
            // 从右往左
            while (currentCol != leftTopCol) {
                result.add(matrix[rightBottomRow][currentCol]);
                currentCol--;
            }
            // 从下往上
            while (currentRow != leftTopRow) {
                result.add(matrix[currentRow][leftTopCol]);
                currentRow--;
            }
        }
    }
}
