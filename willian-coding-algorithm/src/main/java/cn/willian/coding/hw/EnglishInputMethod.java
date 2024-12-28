package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-28 23:34:33
 */
// 序号：4
// 标题：英文输入法
// 主管期望你来实现英文输入法单词联想功能。需求如下：
// 依据用户输入的单词前缀，从已输入的英文语句中联想出用户想输入的单词，按字典序输出联想到的单词序列，如果联想不到，请输出用户输入的单词前缀。
// 注意：
// 1.英文单词联想时，区分大小写，
// 2.缩略形式如“don't”，判定为两个单词， “Don”和“t”
// 3.输出的单词序列，不能有重复单词，且只能是英文单词，不能有标点符号。
// 输入描述：
//
// 输入为两行。
// 首行输入一段由英文单词word和标点符合组成的语句str；
// 接下来一行为一个英文单词前缀pre。
// 0＜word.length()＜＝20
// 0＜str.length()＜＝10000
// 0＜pre＜＝20
//
// 输出描述：
// 输出符合要求的单词序列或单词前缀，存在多个时，单词之间以单个空格分割
//
// 示例1：
// 输入
// I love you
// He
//
// 输出
// He
public class EnglishInputMethod {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        String pre = sc.nextLine();

        // 使用正则表达式分割字符串
        String[] words = word.split("[^a-zA-Z]");
        // 利用Set进行去重
        List<String> result = new ArrayList<>(new HashSet<>(Arrays.asList(words)));
        // 对去重后的数组进行排序
        result.sort(null);
        // 过滤掉不以pre开头的单词
        result.removeIf(x -> !x.startsWith(pre));

        if (!result.isEmpty()) {
            System.out.println(String.join(" ", result));
        } else {
            System.out.println(pre);
        }
    }
}
