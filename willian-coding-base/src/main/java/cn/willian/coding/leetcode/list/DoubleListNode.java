package cn.willian.coding.leetcode.list;

import java.util.Objects;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 09:26:32
 */
@Data
public class DoubleListNode {

    public int val;
    public DoubleListNode prev;
    public DoubleListNode next;

    DoubleListNode() {}

    DoubleListNode(int val) {
        this.val = val;
    }

    DoubleListNode(int val, DoubleListNode prev, DoubleListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;

    }

    public static void print(DoubleListNode head) {

        if (Objects.isNull(head)) {
            return;
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append(head.getVal());
        sb2.append(head.getVal());
        while (head.next != null) {
            sb1.append(" -> ").append(head.next.getVal());
            sb2.append(" <- ").append(head.next.getVal());
            head = head.next;
        }
        System.out.println(sb1);
        System.out.println(sb2);
        System.out.println("---------------------");
    }

    public static DoubleListNode init() {

        DoubleListNode first = new DoubleListNode(1);
        DoubleListNode second = new DoubleListNode(2);
        DoubleListNode three = new DoubleListNode(3);
        DoubleListNode four = new DoubleListNode(4);
        DoubleListNode five = new DoubleListNode(5);

        first.next = second;

        second.prev = first;
        second.next = three;

        three.prev = second;
        three.next = four;

        four.prev = three;
        four.next = five;

        five.prev = four;

        return first;
    }
}
