package cn.willian.coding.leetcode.list;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 10:49:48
 */
// https://leetcode.cn/problems/swap-nodes-in-pairs/
// 24. 两两交换链表中的节点
// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
public class SwapLinkedListNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode.print(head);
        ListNode newHead = swapPairs(head);
        ListNode.print(newHead);
    }

    public static ListNode swapPairs(ListNode head) {

        // 定义一个虚拟节点
        ListNode virtual = new ListNode();
        virtual.next = head;

        ListNode cur = virtual;
        while (cur.next != null && cur.next.next != null) {
            ListNode node1 = cur.next;
            ListNode node2 = node1.next;
            ListNode node3 = node2.next;

            cur.next = node2;
            node2.next = node1;
            node1.next = node3;

            cur = cur.next.next;
        }
        return virtual.next;
    }
}
