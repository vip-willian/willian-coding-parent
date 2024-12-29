package cn.willian.coding.hw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 21:03:55
 */
// 序号15
// 标题：最大值
// 给定一组数（非负），重排顺序后输出一个最大的整数。
// 示例1：
// 输入：[10,9]
// 输出: 910
// 说明：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
//
// 输入描述：
// 数字组合
//
// 输出描述：
// 最大的整数
//
// 示例1：
// 输入
// 10 9
//
// 输出
// 910
public class MaxValue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] numStrings = sc.nextLine().split(" ");

        // 自定义排序
        Arrays.sort(numStrings, (a, b) -> (b + a).compareTo(a + b));

        // 特殊处理，如果第一个数字为0
        if (numStrings[0].equals("0")) {
            System.out.println("0");
            return;
        }
        // 拼接所有字符串
        StringBuilder sb = new StringBuilder();
        for (String numString : numStrings) {
            sb.append(numString);
        }
        System.out.println(sb);
    }
}
