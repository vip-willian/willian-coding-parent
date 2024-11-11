package cn.willian.coding.leetcode.stack;

import lombok.Data;

import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-30 19:12:54
 */
public class LinkedStackDemo {

    public static void main(String[] args) {

        LinkedStack linkedStack = new LinkedStack();

        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);

        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
    }

    static class LinkedStack {

        private Node top;

        public boolean isEmpty() {
            return top == null;
        }

        public void push(int data) {

            Node node = new Node(data);
            if (isEmpty()) {
                top = node;
                return;
            }
            node.pre = top;
            top = node;
        }

        public int pop() {

            if (isEmpty()) {
                throw new RuntimeException("栈空");
            }
            int value = top.value;
            top = top.pre;
            return value;
        }
    }

    @Data
    static class Node {

        private int value;
        private Node pre;

        public Node(int value) {
            this.value = value;
        }
    }
}
