package cn.willian.coding.leetcode.array;

import java.util.Arrays;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-05 11:05:09
 */
// 59.螺旋矩阵II
// 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
// 示例:
// 输入: 3 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
public class SpiralMatrix {

    public static void main(String[] args) {

        int[][] nums = generateMatrix(3);

        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    public static int[][] generateMatrix(int n) {

        // 定义一个二维数组
        int[][] matrix = new int[n][n];
        // 定义遍历开始的行和列
        int startX = 0, startY = 0;
        // 定义要填充的数
        int num = 1;
        // 定义要循环的圈数
        int loop = 1;
        // 定义每一次打圈的一个偏移量
        int offset = 1;
        // 定义列
        int row, col;

        // 总循环的圈数 是 n / 2
        // 假设n = 5,最多填充2圈，n = 3, 填充1圈
        while (loop <= n / 2) {
            // 从左上到右上填充到n-offset
            for (col = startY; col < n - offset; col++) {
                matrix[startX][col] = num++;
            }
            // 从右上到右下填充到n-offset
            for (row = startX; row < n - offset; row++) {
                matrix[row][col] = num++;
            }
            // 从右下到左下填充到(开始列)startY
            for (; col > startY; col--) {
                matrix[row][col] = num++;
            }
            // 从左下到左上填充到(开始行)startX
            for (; row > startX; row--) {
                matrix[row][col] = num++;
            }
            // 增加开始位置，填充下一圈
            startX++;
            startY++;
            offset++;
            loop++;
        }
        // 如果圈数是奇数的话，上面遍历完成之后，最中间一个还没填充
        if (n % 2 == 1) {
            matrix[startX][startY] = num;
        }
        return matrix;
    }
}
