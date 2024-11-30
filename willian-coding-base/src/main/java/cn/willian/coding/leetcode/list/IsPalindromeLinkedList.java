package cn.willian.coding.leetcode.list;

import java.util.Stack;

/**
 * 是否为回文链表
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-30 10:43:29
 */
@SuppressWarnings("all")
public class IsPalindromeLinkedList {

    public static void main(String[] args) {

        boolean palindrome1 = isPalindrome3(ListNode.buildNode4());
        System.out.println("链表4 回文校验结果：" + palindrome1);

        boolean palindrome2 = isPalindrome3(ListNode.buildNode3());
        System.out.println("链表3 回文校验结果：" + palindrome2);
    }

    /**
     * 使用栈空间，将原来的全部元素压栈
     */
    public static boolean isPalindrome1(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        // 将链表数据放入栈中
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 从栈中取出元素做对比
        ListNode pop;
        while (!stack.isEmpty() && (pop = stack.pop()) != null && head != null) {
            if (head.val != pop.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 使用栈空间，将中点之后的元素压栈
     */
    public static boolean isPalindrome2(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        // 将链表中点后的数据放入栈中
        ListNode cur = getMiddleNode(head);
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode pop;
        while (!stack.isEmpty() && (pop = stack.pop()) != null && head != null) {
            if (head.val != pop.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 不使用额外空间，从中点之后反转链表再比较
     */
    public static boolean isPalindrome3(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }
        ListNode n1 = head;
        ListNode n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 此时n1是靠近左边的中点位置
        n2 = n1.next;
        n1.next = null;
        ListNode n3 = null;
        // 反转链表
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        // 校验是否是回文
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.val != n2.val) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 反转链表
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    // 中间节点靠左边
    private static ListNode getMiddleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
