package cn.willian.coding.hw;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 13:57:44
 */
// 序号8
// 标题：找朋友
// 在学校中，N个小朋友站成一队，第i个小朋友的身高为height[i]，第i个小朋友可以看到的第一个比自己
// 身高更高的小朋友j。那么j是i的好朋友(要求j＞i），请重新生成一个列表，对应位置的输出是每个小朋友的
// 好朋友位置，如果没有看到好朋友，请在该位置用0代替。
// 小朋友人数范围是[0,40000]，
//
// 输入描述：
// 第一行输入N，N表示有N个小朋友
// 第二行输入N个小朋友的身高height[i],都是整数
//
// 输出描述:
// 输出N个小朋友的好朋友的位置
//
// 示例1：
// 输入
//
// 2
// 100 95
//
// 输出
//
// 0 0

// 示例2：
// 输入
// 8
// 123 124 125 121 119 122 126 123
// 输出
// 1 2 6 5 5 6 0 0
public class FindFriend {
    // 找到右边比它大的第一个数
    // 单调栈方式
    public static void main(String[] args) {
        // 输入
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<Integer> heights =
            Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        // 从后往前寻找好朋友，最后一个肯定没有好朋友
        for (int i = n - 1; i >= 0; i--) {
            // 如果之前放进入的值比当前的值还要小，说明一定不是好朋友，移出去
            while (!stack.isEmpty() && heights.get(stack.peek()) <= heights.get(i)) {
                stack.pop();
            }
            // 移除之后，如果还剩余，那肯定就是第一个最大的
            if (!stack.isEmpty()) {
                result[i] = stack.peek();
            }
            stack.push(i);
        }

        // 输出结果
        for (int i = 0; i < n; i++) {
            System.out.print(result[i]);
            if (i != n - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
