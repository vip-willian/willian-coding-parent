package cn.willian.coding.leetcode.graph;

import java.util.Arrays;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-04 21:42:24
 */
// https://leetcode.cn/problems/rotate-image/description
// 48. 旋转图像
// 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。你必须在 原地 旋转图像，
// 这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

// 示例1
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
// 输出：[[7,4,1],[8,5,2],[9,6,3]]

// 示例 2
// 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
// 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
public class Code48_RotateImage {

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void rotate(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int leftTopRow = 0;
        int leftTopCol = 0;
        int rightTopRow = matrix.length - 1;
        int rightTopCol = matrix[0].length - 1;

        while (leftTopRow <= rightTopRow && leftTopCol <= rightTopCol) {
            rotate(matrix, leftTopRow++, leftTopCol++, rightTopRow--, rightTopCol--);
        }
    }

    public static void rotate(int[][] matrix, int leftTopRow, int leftTopCol, int rightBottomRow, int rightBottomCol) {

        // 定义旋转的有多少组
        int group = rightBottomCol - leftTopCol;
        for (int i = 0; i < group; i++) {
            // 每一组中要调整的四个位置
            // 左上
            int temp = matrix[leftTopRow][leftTopCol + i];
            // 左上 = 左下
            matrix[leftTopRow][leftTopCol + i] = matrix[rightBottomRow - i][leftTopCol];
            // 左下=右下
            matrix[rightBottomRow - i][leftTopCol] = matrix[rightBottomRow][rightBottomCol - i];
            // 右下 = 右上
            matrix[rightBottomRow][rightBottomCol - i] = matrix[leftTopRow + i][rightBottomCol];
            // 右上 = 左上
            matrix[leftTopRow + i][rightBottomCol] = temp;
        }
    }
}
