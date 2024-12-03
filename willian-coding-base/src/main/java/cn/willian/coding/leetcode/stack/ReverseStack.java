package cn.willian.coding.leetcode.stack;

import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-02 09:59:30
 */
public class ReverseStack {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        reverseStack(stack);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    private static void reverseStack(Stack<Integer> stack) {

        if (stack.isEmpty()) {
            return;
        }
        // 获取栈底元素
        int i = getStackLast(stack);
        reverseStack(stack);
        stack.push(i);
    }

    private static int getStackLast(Stack<Integer> stack) {

        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = getStackLast(stack);
        stack.push(result);
        return last;
    }
}
