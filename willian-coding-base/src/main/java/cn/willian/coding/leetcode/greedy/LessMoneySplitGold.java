package cn.willian.coding.leetcode.greedy;

import java.util.PriorityQueue;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 23:35:25
 */
public class LessMoneySplitGold {

    public static void main(String[] args) {
        int[] gold = {20, 30, 10};
        System.out.println(lessMoney(gold));
    }

    public static int lessMoney(int[] arr) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int value : arr) {
            queue.add(value);
        }
        int sum = 0;
        int cur;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

}
