package cn.willian.coding.designmode.behavior.command;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:47:41
 */
@Data
public class Application {
    /**
     * 剪切板内容
     */
    private String clipboard;
    /**
     * 编辑器
     */
    private Editor editor;
    /**
     * 执行命令历史
     */
    private CommandHistory commandHistory;

    public Application(Editor editor) {
        this.commandHistory = new CommandHistory();
        this.editor = editor;
    }

    public void execute(CommandEvent command) {

        if (CommandEvent.COPY == command) {
            execute(new CopyCommand(this, editor));
        } else if (CommandEvent.PASTE == command) {
            execute(new PasteCommand(this, editor));
        } else if (CommandEvent.CUT == command) {
            execute(new CutCommand(this, editor));
        } else if (CommandEvent.REPLACE == command) {
            execute(new ReplaceCommand(this, editor, "文本内容被替换了"));
        } else if (CommandEvent.UNDO == command) {
            execute(new UndoCommand(this, editor));
        }
    }

    public void cancel(Command command) {

        // 撤销命令
        command.undo();
    }

    private void execute(Command command) {

        // 执行命令，记录操作历史
        if (command.execute()) {
            commandHistory.push(command);
        }
    }

    /**
     * 执行具体的撤销
     */
    public void undo() {

        Command command = commandHistory.pop();
        if (command != null) {
            command.undo();
        }
    }
}
