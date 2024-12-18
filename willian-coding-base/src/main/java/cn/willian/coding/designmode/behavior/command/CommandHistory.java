package cn.willian.coding.designmode.behavior.command;

import java.util.Stack;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 18:02:30
 */
public class CommandHistory {

    /**
     * 保存执行的命令
     */
    private final Stack<Command> commands;

    public CommandHistory() {
        this.commands = new Stack<>();
    }

    // 将命令压入历史记录数组的末尾
    public void push(Command command) {
        commands.push(command);
    }

    // 从历史记录中取出最近的命令
    public Command pop() {
        return commands.pop();
    }
}
