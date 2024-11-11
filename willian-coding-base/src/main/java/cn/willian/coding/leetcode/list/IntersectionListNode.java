package cn.willian.coding.leetcode.list;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 11:39:00
 */
// https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci
// 7. 链表相交
// 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
// 题目数据 保证 整个链式结构中不存在环。
// 注意，函数返回结果后，链表必须 保持其原始结构 。

// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
// 输出：Intersected at '8'
// 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
// 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
// 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
public class IntersectionListNode {

    public static void main(String[] args) {
        ListNode sameNode = new ListNode(8, new ListNode(4, new ListNode(5)));
        ListNode headA = new ListNode(4, new ListNode(1, sameNode));
        ListNode headB = new ListNode(5, new ListNode(0, new ListNode(1, sameNode)));

        ListNode.print(getIntersectionNode(headA, headB));
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        ListNode curA = headA;
        ListNode curB = headB;

        int lengthA = getLength(curA);
        int lengthB = getLength(curB);

        // 重置A、B头节点的位置
        curA = headA;
        curB = headB;
        // 进行交换
        if (lengthB > lengthA) {
            int tempLength = lengthA;
            lengthA = lengthB;
            lengthB = tempLength;

            ListNode temp = curA;
            curA = curB;
            curB = temp;
        }
        // 计算长度差
        int gap = lengthA - lengthB;
        while (gap-- > 0) {
            curA = curA.next;
        }
        // 开始同时移动
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }

    public static int getLength(ListNode head) {

        int length = 0;
        while (head.next != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
