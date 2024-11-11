package cn.willian.coding.coll;

import lombok.Data;

/**
 * 单向环形链表
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-30 14:11:10
 */
public class Josepfu {

    public static void main(String[] args) {
        SingleCircleLinkedList singleCircleLinkedList = new SingleCircleLinkedList(5);
        singleCircleLinkedList.show();
    }

    public void a(int p, int k) {

    }


    @Data
    static class Node {

        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 单向环形链表
    @Data
    static class SingleCircleLinkedList {

        private static Node first = null;

        // 初始化环形链表
        public SingleCircleLinkedList(int numbers) {

            if (numbers < 1) {
                return;
            }
            Node current = null;
            for (int i = 1; i <= numbers; i++) {
                Node node = new Node(i);
                if (i == 1) {
                    first = node;
                    first.next = first;
                    current = first;
                } else {
                    current.next = node;
                    node.next = first;
                    current = node;
                }
            }
        }

        public void show() {

            if (first == null) {
                return;
            }
            Node current = first;
            while (true) {
                System.out.println(current.value);
                if (current.next == first) {
                    return;
                }
                current = current.next;
            }
        }
    }
}