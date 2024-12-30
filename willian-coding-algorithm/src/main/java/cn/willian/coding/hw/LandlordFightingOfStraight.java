package cn.willian.coding.hw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-29 09:59:22
 */
// 序号1
// 题目：斗地主之顺子
// 在斗地主扑克牌游戏中，扑克牌儿由小到大的顺序为：3,4,5,6,7,8,9,10,J,Q,K,A,2,玩家可以出的扑克牌阵型有单张、对子、顺子、飞机、炸弹等。
// 其中的顺子的出牌规则为:由至少5张由小到大连续递增的扑克牌组成，且不能包含2，例如:{3,4,5,6,7}、｛3,4,5,6,7,8,9,10,J,Q,K,A}都是有效的顺子
// ;而{J,Q,K,A,2}、｛2,3,4,5,6}、{3,4,5,6}、｛3,4,5,6,8}等都不是顺子。
// 给定一个包含13张牌的数组，如果有满足出牌规则的顺子，请输出顺子。
// 如果存在多个顺子，请每行输出一个顺子，且需要按顺子的第一张牌的大小（必须从小到大）依次输出。
// 如果没有满足出牌规则的顺子，请输出NO。
//
// 输入描述：
// 13张任意顺子的扑克牌，每张扑克牌数字用空格隔开，每张扑克牌的数字都是合法的，并且不包括大小王：
// 2 9 J 2 3 4 K A 7 9 A 5 6
// 不需要考虑输入为异常字符的情况。

// 输出描述:
// 组成的顺子，每张扑克牌数字用空格隔开:
// 3 4 5 6 7
//
// 示例1：
// 输入
// 2 9 J 2 3 4 K A 7 9 A 5 6
//
// 输出
// 3 4 5 6 7
public class LandlordFightingOfStraight {

    private static final String[] SHUN = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : SHUN) {
            map.put(s, 0);
        }
        // 读取用户输入的牌
        Scanner sc = new Scanner(System.in);
        String[] cards = sc.nextLine().split(" ");
        for (String card : cards) {
            if (map.containsKey(card)) {
                map.put(card, map.get(card) + 1);
            }
        }
        // 定义一个变量，是否不存在顺子
        boolean isNotExistStraight = true;
        for (int i = 0; i < SHUN.length + 1; i++) {
            // 获取连续的牌的个数
            int count = getContinuousCardCount(i, map);
            // 连续的个数大于等于5个，说明存在顺子
            if (count >= 5) {
                // 打印顺子
                print(i, count);
                // 清除已经使用的牌
                clear(i, count, map);
                // 更新标志位
                isNotExistStraight = false;
            }
        }
        // 如果不存在顺子，输出
        if (isNotExistStraight) {
            System.out.println("No");
        }
        sc.close();
    }

    public static void clear(int i, int count, Map<String, Integer> map) {
        for (int j = i; j < i + count; j++) {
            map.put(SHUN[j], map.get(SHUN[j]) - 1);
        }
    }

    public static void print(int i, int count) {
        for (int j = i; j < i + count; j++) {
            System.out.print(SHUN[j] + " ");
        }
        System.out.println();
    }

    public static int getContinuousCardCount(int i, Map<String, Integer> map) {

        int res = 0;
        for (int j = i; j < SHUN.length; j++) {
            if (map.get(SHUN[j]) >= 1) {
                res++;
            } else {
                break;
            }
        }
        return res;
    }
}
