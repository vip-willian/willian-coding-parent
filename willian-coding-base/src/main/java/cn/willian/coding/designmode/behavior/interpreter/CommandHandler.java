package cn.willian.coding.designmode.behavior.interpreter;

import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 16:50:25
 */
// 指令处理类：工具类
public class CommandHandler {

    private Expression expression;

    public void handle(String command) {
        Expression left;
        Expression right;
        Expression direction;
        Expression action;
        Expression number;

        Stack<Expression> stack = new Stack<>();

        String[] words = command.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // 如果遇到“and”，则将其后的三个单词作为三个终结符表达式连成一个简单句子SentenceNode作为“and”的右表达式，而将从栈顶弹出的表达式作为“and”的左表达式，最后将新的“and”表达式压入栈中。
            if (word.equalsIgnoreCase("and")) {
                left = stack.pop(); // 弹出栈顶表达式作为左表达式
                String word1 = words[++i];
                direction = new DirectionExpression(word1);
                String word2 = words[++i];
                action = new ActionExpression(word2);
                String word3 = words[++i];
                number = new DistanceExpression(word3);
                right = new SentenceExpression(direction, action, number); // 右表达式
                stack.push(new AndExpression(left, right)); // 将新表达式压入栈中
            } else {
                // 如果是从头开始进行解释，则将前三个单词组成一个简单句子SentenceNode并将该句子压入栈中
                String word1 = words[i];
                direction = new DirectionExpression(word1);
                String word2 = words[++i];
                action = new ActionExpression(word2);
                String word3 = words[++i];
                number = new DistanceExpression(word3);
                left = new SentenceExpression(direction, action, number);
                stack.push(left); // 将新表达式压入栈中
            }
        }
        this.expression = stack.pop(); // 将全部表达式从栈中弹出
    }

    public String output() {
        return expression.interpret(); // 解释表达式
    }
}
