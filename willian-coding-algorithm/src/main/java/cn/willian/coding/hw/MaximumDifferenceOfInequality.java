package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-31 17:00:16
 */
public class MaximumDifferenceOfInequality {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(";");
        double[][] arr = new double[3][5];
        int[] coefficient = new int[5];
        double[] inputResult = new double[3];
        int max = Integer.MIN_VALUE;

        // 初始化a11-a35
        int index = 0;
        String[] segment;
        for (int i = 0; i < 3; i++) {
            segment = split[i].split(",");
            index = 0;
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Double.parseDouble(segment[index++]);
            }
        }

        // 初始化系数
        index = 0;
        segment = split[3].split(",");
        for (int i = 0; i < 5; i++) {
            coefficient[i] = Integer.parseInt(segment[index++]);
        }

        // 初始化不等式的右边
        index = 0;
        segment = split[4].split(",");
        for (int i = 0; i < 3; i++) {
            inputResult[i] = Double.parseDouble(segment[index++]);
        }

        // 计算并判断
        segment = split[5].split(",");
        boolean isRight = true;
        for (int i = 0; i < 3; i++) {
            double sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += arr[i][j] * coefficient[j];
            }

            if (isRight) {
                if (segment[i].equals(">")) {
                    isRight = sum > inputResult[i];
                } else if (segment[i].equals(">=")) {
                    isRight = sum >= inputResult[i];
                } else if (segment[i].equals("=")) {
                    isRight = sum == inputResult[i];
                } else if (segment[i].equals("<=")) {
                    isRight = sum <= inputResult[i];
                } else if (segment[i].equals("<")) {
                    isRight = sum < inputResult[i];
                }
            }
            max = (int)Math.max(max, sum - inputResult[i]);
        }

        System.out.println(isRight + " " + max);
    }
}
