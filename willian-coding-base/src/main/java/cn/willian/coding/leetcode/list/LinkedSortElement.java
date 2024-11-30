package cn.willian.coding.leetcode.list;

/**
 * 链表元素按指定规则的排序
 *
 * 4 6 3 5 8 5 2 5
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 12:00:24
 */
public class LinkedSortElement {

    public static void main(String[] args) {

        ListNode node = new ListNode(4, new ListNode(6, new ListNode(3, new ListNode(5, new ListNode(8, new ListNode(5, new ListNode(2, new ListNode(5))))))));
        ListNode newCode = sort(node, 5);
        ListNode.print(newCode);
    }

    public static ListNode sort(ListNode head, int target) {

        ListNode sHead = null;
        ListNode sTail = null;

        ListNode eHead = null;
        ListNode eTail = null;

        ListNode gHead = null;
        ListNode gTail = null;
        ListNode next = null;

        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            if (cur.val < target) {
                if (sTail == null) {
                    sHead = cur;
                    sTail = cur;
                } else {
                    sTail.next = cur;
                    sTail = cur;
                }
            }
            if (cur.val == target) {
                if (eTail == null) {
                    eHead = cur;
                    eTail = cur;
                } else {
                    eTail.next = cur;
                    eTail = cur;
                }
            }
            if (cur.val > target) {
                if (gTail == null) {
                    gHead = cur;
                    gTail = cur;
                } else {
                    gTail.next = cur;
                    gTail = cur;
                }
            }
            cur = next;
        }

        if (sTail != null) {
            sTail.next = eHead;
            eTail = eTail == null ? sTail : eTail;
        }
        if (eTail != null) {
            eTail.next = gHead;
        }
        return (sHead != null) ? sHead : (eHead != null ? eHead : gHead);
    }
}
