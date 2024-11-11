package cn.willian.coding.leetcode.list;


/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 09:27:58
 */
// https://leetcode.cn/problems/remove-linked-list-elements/description/
// 203. 移除链表元素
// 题意：删除链表中等于给定值 val 的所有节点。
// 示例 1： 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
// 示例 2： 输入：head = [], val = 1 输出：[]
// 示例 3： 输入：head = [7,7,7,7], val = 7 输出：[]
public class RemoveLinkedElement {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        ListNode.print(head);
        ListNode newHead = removeElements(head, 6);
        ListNode.print(newHead);
    }

    public static ListNode removeElements(ListNode head, int val) {

        // 定义一个临时的节点
        ListNode virtual = new ListNode();
        virtual.next = head;

        ListNode temp = virtual;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return virtual.next;
    }
}

