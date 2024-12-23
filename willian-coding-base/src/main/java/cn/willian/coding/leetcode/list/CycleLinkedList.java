package cn.willian.coding.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 12:09:10
 */
// https://leetcode.cn/problems/linked-list-cycle-ii/description/
// 142. 环形链表 II
// 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
// 不允许修改 链表。
// 示例
// 输入：head = [3,2,0,-4], pos = 1
// 输出：返回索引为 1 的链表节点
// 解释：链表中有一个环，其尾部连接到第二个节点。
public class CycleLinkedList {

    private static Map<ListNode, Integer> map = new HashMap<>();

    public static ListNode detectCycle1(ListNode head) {

        ListNode cur = head;
        int index = 0;
        while (!map.containsKey(cur) && cur != null) {
            map.put(cur, index);
            cur = cur.next;
            index++;
        }
        return cur;
    }

    // 快慢指针，快指针先走2步，慢指针走1步
    // 相交时，慢指针停留在某个位置
    // 让快指针从头指针重新触发，快慢指针重新相遇
    // 返回的是入环的第一个节点
    public static ListNode detectCycle2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode index1 = fast;
                ListNode index2 = head;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
