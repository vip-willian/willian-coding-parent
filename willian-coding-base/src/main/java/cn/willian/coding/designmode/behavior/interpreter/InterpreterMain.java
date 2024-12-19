package cn.willian.coding.designmode.behavior.interpreter;

/**
 * 机器人控制程序<br>
 * 在该机器人控制程序中包含一些简单的英文控制指令，每一个指令对应一个表达式(expression)，<br>
 * 该表达式可以是简单表达式也可以是复合表达式，每一个简单表达式由移动方向(direction)，移动方式(action)和移动距离(distance)三部分组成，<br>
 * 其中移动方向包括上(up)、下(down)、左(left)、右(right)；移动方式包括移动(move)和快速移动(run)；移动距离为一个正整数。<br>
 * 两个表达式之间可以通过与(and)连接，形成复合(composite)表达式。<br>
 *
 * 用户通过对图形化的设置界面进行操作可以创建一个机器人控制指令，机器人在收到指令后将按照指令的设置进行移动，<br>
 * 例如输入控制指令：up move 5，则“向上移动5个单位”；输入控制指令：down run 10 and left move 20，<br>
 * 则“向下快速移动10个单位再向左移动20个单位”。<br>
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 16:12:02
 */
public class InterpreterMain {
    public static void main(String[] args) {

        String command = "up move 5 and left run 10 and down move 5";

        CommandHandler handler = new CommandHandler();
        handler.handle(command);

        System.out.println(handler.output());
    }
}
