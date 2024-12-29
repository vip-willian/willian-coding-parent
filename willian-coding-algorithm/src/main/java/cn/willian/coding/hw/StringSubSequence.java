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
        String target = sc.nextLine();
        String source = sc.nextLine();

        int targetLength = target.length();
        int sourceLength = source.length();

        // 逆序遍历
        int i = targetLength - 1;
        int j = sourceLength - 1;
        // 是否有匹配的子序列
        boolean flag = false;
        while (i >= 0 && j >= 0) {
            if (target.charAt(i) == source.charAt(j)) {
                // 目标字符串遍历完毕，说明匹配到了，标记位更新
                if (i == 0) {
                    flag = true;
                    System.out.println(j);
                }
                // 当前配置匹配上了，匹配下一个位置
                i--;
            }
            // 不匹配，往下一位去寻找
            j--;
        }
        if (!flag) {
            System.out.println(-1);
        }
    }
}
