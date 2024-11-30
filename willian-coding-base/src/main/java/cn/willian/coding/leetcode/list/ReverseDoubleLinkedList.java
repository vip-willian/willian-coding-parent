package cn.willian.coding.leetcode.list;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 10:32:26
 */
// https://leetcode.cn/problems/design-linked-list/
// 反转双向链表
// 给你双向链表的头节点 head ，请你反转链表，并返回反转后的链表。
public class ReverseDoubleLinkedList {

    public static void main(String[] args) {

        DoubleListNode head = DoubleListNode.init();
        DoubleListNode.print(head);
        DoubleListNode newHead = reverseList(head);
        DoubleListNode.print(newHead);
    }

    public static DoubleListNode reverseList(DoubleListNode head) {

        DoubleListNode cur = head;
        DoubleListNode pre = null;
        DoubleListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.prev = next;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
