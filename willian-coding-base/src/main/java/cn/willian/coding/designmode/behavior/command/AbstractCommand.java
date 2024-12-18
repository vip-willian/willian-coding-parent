package cn.willian.coding.designmode.behavior.command;

import cn.willian.coding.designmode.behavior.command.receiver.Application;
import cn.willian.coding.designmode.behavior.command.receiver.Editor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:48:25
 */
public abstract class AbstractCommand implements Command {

    // 这2个都是命令具体的接收者
    protected Application application;
    protected Editor editor;

    protected String backup;

    public AbstractCommand(Application application, Editor editor) {
        this.application = application;
        this.editor = editor;
    }

    protected void saveBackup() {
        backup = editor.getContent();
    }

    protected void cleanBackup() {
        backup = "";
    }

    @Override
    public boolean execute() {
        return false;
    }
}
