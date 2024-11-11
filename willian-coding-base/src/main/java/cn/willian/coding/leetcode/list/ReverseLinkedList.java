package cn.willian.coding.leetcode.list;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 10:32:26
 */
// https://leetcode.cn/problems/design-linked-list/
// 206. 反转链表
// 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 示例: 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode.print(head);
        ListNode newHead = reverseList(head);
        ListNode.print(newHead);
    }

    public static ListNode reverseList(ListNode head) {

        // 定义当前节点指针
        ListNode cur = head;
        // 定义节点的前一个节点
        ListNode pre = null;
        // 临时记录当前节点的下一个
        ListNode temp;

        while (cur != null) {
            // 保存当前节点的下一个节点
            temp = cur.next;
            // 将当前节点的下一个反转
            cur.next = pre;
            // 移动上一个节点指针
            pre = cur;
            // 移动当前节点指针
            cur = temp;
        }
        return pre;
    }
}
