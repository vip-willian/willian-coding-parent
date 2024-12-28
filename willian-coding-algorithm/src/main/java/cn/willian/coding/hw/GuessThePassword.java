package cn.willian.coding.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-28 22:29:32
 */
// 序号2
// 题目：猜密码
// 小杨申请了一个保密柜，但是他忘记了密码，只记得密码都是数字，而且所有数字都是不重复的。请你根据他记住的数字范围和密码的最小数字数量，帮他算一下有哪些可能的组合。规则如下：
// 1、输出的组合都是从可选的数字范围中选取的，且不能重复。
// 2，输出的密码数字要按照从小到大的顺序排列，密码组合需要按照字母顺序，从小到大的顺序排序。
// 3、输出的每一个组合的数字的数量要大于等于密码最小数字数量；
// 4、如果可能的组合为空，则返回“None”
//
// 输入描述：
// 1、输入的第一行是可能的密码数字列表，数字间以半角逗号分隔
// 2、输入的第二行是密码最小数字数量
//
// 输出描述：
// 可能的密码组合，每种组合显示成一行，每个组合内部的数字以半角逗号分隔，以从小到大的顺序排列。输出的组合间需要按照字典排序。
// 比如：2,3,4放到2,4的前面
//
// 备注：
// 字典序是指按照单词出现在字典的顺序进行排序的方法，比如：
// a排在b前
// a排在ab前
// ab排在ac前
// ac排在aca前
//
// 示例1：
// 输入
// 2,3,4
// 2
//
// 输出
// 2,3
// 2,3,4
// 2,4
// 3,4
public class GuessThePassword {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 密码数字列表
        String line = sc.nextLine();
        // 获取密码最小数字量
        int minCount = sc.nextInt();

        List<String> nums = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(line, ",");
        while (st.hasMoreTokens()) {
            nums.add(st.nextToken());
        }
        Collections.sort(nums);

        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();

        // 回溯从第0个元素开始找
        dfs(nums, 0, minCount, path, result);

        if (!result.isEmpty()) {
            for (String s : result) {
                System.out.println(s);
            }
        } else {
            System.out.println("None");
        }
    }

    public static void dfs(List<String> nums, int index, int minCount, List<String> path, List<String> result) {

        // 终止条件
        if (path.size() >= minCount) {
            // 满足条件，放入到结果集
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(path.get(i));
            }
            result.add(sb.toString());
        }
        // 已经遍历完整个输入的字符了
        if (path.size() == nums.size()) {
            return;
        }
        // 回溯开始查找
        for (int i = index; i < nums.size(); i++) {
            // 放入遍历的第一个数
            path.add(nums.get(i));
            dfs(nums, i + 1, minCount, path, result);
            path.remove(path.size() - 1);
        }
    }
}
