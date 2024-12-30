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
        while (sc.hasNext()) {
            String[] numStrings = sc.nextLine().split(" ");
            // 进行排序
            Arrays.sort(numStrings, (a, b) -> (b + a).compareTo(a + b));
            if ("0".equals(numStrings[0])) {
                System.out.println("0");
            } else {
                System.out.println(String.join("", numStrings));
            }
        }
        sc.close();
    }
}
