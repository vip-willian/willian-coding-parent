package cn.willian.coding.hw;

import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 20:49:44
 */
// 序号14
// 标题：判断字符串子序列。
// 给定字符串target和source，判断target是否为source的子序列。你可以认为target和source中仅
// 包含英文小写字母，字符串source可能会很长（长度～=500,000）而target是个短字符串（长度＜
// =100）。字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的
// 新字符串。（例如，
// "abc"是aebycd的一个子序列，而"ayb"不是)。
// 请找出最后一个子序列的起始位置。
// 输入描述：
//
// 第一行为target，短字符长度（长度＜=100）
// 第二行为source，长度字符串（长度～=500,000)
//
// 输出描述:
// 最后一个子序列的起始位置即最后一个子序列首字母的下标
// 备注:
//
// 若在source中找不到target，则输出-1
//
// 示例1：
// 输入
// abc
// abcaybec
//
// 输出
// 3
public class StringSubSequence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String target = sc.nextLine();
            String source = sc.nextLine();
            printSubSequence(target, source);
        }
        sc.close();
    }

    // 双指针逆序遍历
    private static void printSubSequence(String target, String source) {

        int targetLength = target.length();
        int sourceLength = source.length();

        int tIndex = targetLength - 1;
        int sIndex = sourceLength - 1;

        boolean flag = false;
        while (tIndex >= 0 && sIndex >= 0) {
            // 目标字符某一个匹配命中
            if (target.charAt(tIndex) == source.charAt(sIndex)) {
                // 当位置为0都匹配中时，说明子序列
                if (tIndex == 0) {
                    System.out.println(sIndex);
                    flag = true;
                    break;
                }
                // 匹配命中，去下一个位置继续匹配
                tIndex--;
            }
            // 没有匹配命中，从下一个位置继续匹配
            sIndex--;
        }
        // 非子序列，输出-1
        if (!flag) {
            System.out.println(-1);
        }
    }
}
