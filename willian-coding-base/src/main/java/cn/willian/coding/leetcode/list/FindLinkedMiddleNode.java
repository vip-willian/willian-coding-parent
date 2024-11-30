package cn.willian.coding.leetcode.list;

/**
 * 找到链表中间节点
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 10:18:47
 */
public class FindLinkedMiddleNode {

    public static void main(String[] args) {

        ListNode oddNode = ListNode.buildNode1();
        ListNode evenNode = ListNode.buildNode3();

        System.out.println("-----------中点靠近左边--------------");
        System.out.println(getMiddleNodeLeft(oddNode).getVal());
        System.out.println(getMiddleNodeLeft(evenNode).getVal());
        System.out.println("-----------中点靠近右边--------------");
        System.out.println(getMiddleNodeRight(oddNode).getVal());
        System.out.println(getMiddleNodeRight(evenNode).getVal());
    }

    /**
     * 当节点为偶数时，中点为左边节点
     */
    public static ListNode getMiddleNodeLeft(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 当节点为偶数时，中点为右边节点
     */
    public static ListNode getMiddleNodeRight(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
