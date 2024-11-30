package cn.willian.coding.leetcode.list;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-06 09:55:17
 */
// https://leetcode.cn/problems/design-linked-list/description/
// 707. 设计链表
// 你可以选择使用单链表或者双链表，设计并实现自己的链表。
// 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
// 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
//
// 实现 MyLinkedList 类：
//
// MyLinkedList() 初始化 MyLinkedList 对象。
// int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
// void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
// void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
// void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index
// 比长度更大，该节点将 不会插入 到链表中。
// void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
public class MyLinkedList {

    private final ListNode virtual;
    private int size;

    public MyLinkedList() {
        virtual = new ListNode();
        size = 0;
    }

    /**
     * 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1
     *
     * @param index
     * @return
     */
    public int get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode current = virtual;
        // 有虚拟节点存在多加一个
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        return current.val;
    }

    /**
     * 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
     *
     * @param val 值
     */
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = virtual.next;
        virtual.next = newNode;
        size++;
    }

    /**
     * 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
     *
     * @param val 值
     */
    public void addAtTail(int val) {

        ListNode newNode = new ListNode(val);
        ListNode cur = virtual;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        size++;
    }

    /**
     * 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。 如果 index 比长度更大，该节点将 不会插入 到链表中。
     *
     * @param index 下标
     * @param val 值
     */
    public void addAtIndex(int index, int val) {

        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        ListNode cur = virtual;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /**
     * 如果下标有效，则删除链表中下标为 index 的节点。
     *
     * @param index 链表下标
     */
    public void deleteAtIndex(int index) {

        if (index >= size || index < 0) {
            return;
        }
        ListNode cur = virtual;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }
}
