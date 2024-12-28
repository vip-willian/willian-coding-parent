package cn.willian.coding.hw;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-28 23:57:26
 */
// 序号：5
// 标题：玩牌高手
// 给定一个长度为n的整型数组，表示一个选手在n轮内可选择的牌面分数。选手基于规则选牌，请计算所有轮结束后其可以获得的最高总分数。选择规则如下：
// 1.在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。
// 2.选手也可以不选择本轮牌面直接跳转到下一轮，此时将当前总分数还原为3轮前的总分数，若当前轮次小于等于3（即在第1、2、3轮选择跳过轮次），则总分数置为0。
// 3.选手的初始总分数为0，且必须依次参加每一轮。
// 输入描述：
//
// 第一行为一个小写逗号分割的字符串，表示n轮的牌面分数，1＜＝n＜＝20。
// 分数值为整数，-100＜＝分数值＜＝100。
// 不考虑格式问题。
//
// 输出描述：所有轮结束后选手获得的最高总分数。
//
// 示例1：
// 输入
// 1，-5，-6，4，3，6，-2
//
// 输出
// 11
public class PlayCardsMaster {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        // 转成数组
        int[] nums = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getMaxScore(nums));
    }

    public static int getMaxScore(int[] scores) {

        int n = scores.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                // 选择当前牌：nums[i] 可能为负数
                // 不选择当前牌：0
                dp[i] = Math.max(scores[i], 0);
            } else if (i == 1 || i == 2) {
                // 选择当前牌：dp[i - 1] + nums[i]
                // 不选择当前牌：0
                dp[i] = Math.max(dp[i - 1] + scores[i], 0);
            } else {
                // 选择当前牌：dp[i - 1] + nums[i]
                // 不选择当前牌：还原成3轮前的分数
                dp[i] = Math.max(dp[i - 1] + scores[i], dp[i - 3]);
            }
        }
        return dp[n - 1];
    }
}
