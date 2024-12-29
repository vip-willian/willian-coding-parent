package cn.willian.coding.hw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 23:29:24
 */
// 题目描述：
// 计算24点是一种扑克牌益智游戏，随机抽出4张扑克牌，通过加(+)，减(-)，乘(*), 除(/)四种运算法则计算得到整数24，本问题中，扑克牌通过如下字符或者字符串表示，其中，小写joker表示小王，大写JOKER表示大王：
//
// 3 4 5 6 7 8 9 10 J Q K A 2 joker JOKER
//
// 本程序要求实现：输入4张牌，输出一个算式，算式的结果为24点。
//
// 详细说明：
//
// 1.运算只考虑加减乘除运算，没有阶乘等特殊运算符号，没有括号，友情提醒，整数除法要当心，是属于整除，比如2/3=0，3/2=1；
//
// 2.牌面2~10对应的权值为2~10, J、Q、K、A权值分别为为11、12、13、1；
//
// 3.输入4张牌为字符串形式，以一个空格隔开，首尾无空格；如果输入的4张牌中包含大小王，则输出字符串“ERROR”，表示无法运算；
//
// 4.输出的算式格式为4张牌通过+-*/四个运算符相连，中间无空格，4张牌出现顺序任意，只要结果正确；
//
// 5.输出算式的运算顺序从左至右，不包含括号，如1+2+3*4的结果为24，2 A 9 A不能变为(2+1)*(9-1)=24
//
// 6.如果存在多种算式都能计算得出24，只需输出一种即可，如果无法得出24，则输出“NONE”表示无解。
//
// 7.因为都是扑克牌，不存在单个牌为0的情况，且没有括号运算，除数(即分母)的数字不可能为0
//
// 输入描述：
// 输入4张牌为字符串形式，以一个空格隔开，首尾无空格；
//
// 输出描述：
// 输出怎么运算得到24，如果无法得出24，则输出“NONE”表示无解，如果输入的4张牌中包含大小王，则输出字符串“ERROR”，表示无法运算；
//
// 示例：
// 输入：
//
// 4 2 K A
// 输出：
//
// K-A*4/2
// 说明：
//
// A+K*2-4也是一种答案，输出任意一种即可
public class Operation24Point {

    // 建立映射关系
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("10", 10);
            put("J", 11);
            put("Q", 12);
            put("K", 13);
            put("A", 1);
        }
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            // 判断输入的字符串是否包含大小王特殊字符
            if (str.contains("joker") || str.contains("JOKER")) {
                System.out.println("ERROR");
                continue;
            }
            String[] cards = str.split(" ");
            if (!isGet24(cards)) {
                System.out.println("NONE");
            }
        }
    }

    private static boolean isGet24(String[] cards) {

        boolean isGet24 = false;
        boolean[] visited = new boolean[4];
        for (int i = 0; i < 4; i++) {
            visited[i] = true;
            if (dfs(cards, visited, cards[i], map.get(cards[i]))) {
                isGet24 = true;
                break;
            }
            visited[i] = false;
        }
        return isGet24;
    }

    private static boolean dfs(String[] cards, boolean[] visited, String exp, Integer value) {

        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String numStr = cards[i];
                Integer num = map.get(numStr);
                if (
                // 加法
                dfs(cards, visited, exp + "+" + numStr, value + num) ||
                // 减法
                    dfs(cards, visited, exp + "-" + numStr, value - num) ||
                    // 乘法
                    dfs(cards, visited, exp + "*" + numStr, value * num) ||
                    // 除法
                    (value % num == 0 && dfs(cards, visited, exp + "/" + numStr, value / num))) {
                    return true;
                }
                visited[i] = false;
            }
        }
        if (value == 24) {
            System.out.println(exp);
            return true;
        }
        return false;
    }
}
