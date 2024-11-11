package cn.willian.coding.leetcode.list;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 11:19:15
 */
// https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
// 19. 删除链表的倒数第 N 个结点
// 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

// 示例 1：
// 输入：head = [1,2,3,4,5], n = 2
// 输出：[1,2,3,5]

// 示例 2：
// 输入：head = [1], n = 1
// 输出：[]

// 示例 3：
// 输入：head = [1,2], n = 1
// 输出：[1]
public class RemoveLinkedNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode.print(head);
        ListNode newHead = removeNthFromEnd(head, 2);
        ListNode.print(newHead);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode virtual = new ListNode();
        virtual.next = head;

        ListNode fast = virtual;
        ListNode slow = virtual;
        // 先让快指针移动n+1次
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // 再快慢指针同时移动
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 此时慢指针指向的位置就是要删除元素的上一个位置
        slow.next = slow.next.next;
        return virtual.next;
    }
}
