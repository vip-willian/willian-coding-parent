package cn.willian.coding.leetcode.stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-30 19:12:54
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);

        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }

    static class ArrayStack {

        private int top;
        private final int maxSize;
        private final int[] data;

        public ArrayStack(int size) {
            this.maxSize = size;
            this.top = -1;
            this.data = new int[maxSize];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public void push(int data) {

            if(isFull()){
                System.out.println("栈空间已满");
                return;
            }
            top++;
            this.data[top] = data;
        }

        public int pop() {

            if(isEmpty()){
                throw new RuntimeException("栈空");
            }
            int value = data[top];
            top--;
            return value;
        }
    }
}
