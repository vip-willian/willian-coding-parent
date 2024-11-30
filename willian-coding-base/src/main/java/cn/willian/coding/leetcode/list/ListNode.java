package cn.willian.coding.leetcode.list;

import java.util.Objects;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 11:05:36
 */
@Data
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(ListNode head) {

        if (Objects.isNull(head)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(head.getVal());
        while (head.next != null) {
            sb.append(" -> ").append(head.next.getVal());
            head = head.next;
        }
        System.out.println(sb);
    }

    public static ListNode buildNode1() {

        ListNode node5 = new ListNode(6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(5, node4);
        ListNode node2 = new ListNode(2, node3);
        return new ListNode(1, node2);
    }

    public static ListNode buildNode2() {

        ListNode node3 = new ListNode(5);
        ListNode node2 = new ListNode(3, node3);
        return new ListNode(2, node2);
    }

    public static ListNode buildNode3() {

        ListNode node6 = new ListNode(9);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(6, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(4, node3);
        return new ListNode(1, node2);
    }

    public static ListNode buildNode4() {

        ListNode node6 = new ListNode(1);
        ListNode node5 = new ListNode(2, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        return new ListNode(1, node2);
    }
}
