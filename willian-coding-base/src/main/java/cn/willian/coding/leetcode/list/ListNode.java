package cn.willian.coding.leetcode.list;

import lombok.Data;

import java.util.Objects;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 11:05:36
 */
@Data
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

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
}
