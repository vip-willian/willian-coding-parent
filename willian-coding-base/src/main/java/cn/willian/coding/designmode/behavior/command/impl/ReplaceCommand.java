package cn.willian.coding.designmode.behavior.command.impl;

import cn.willian.coding.designmode.behavior.command.AbstractCommand;
import cn.willian.coding.designmode.behavior.command.receiver.Application;
import cn.willian.coding.designmode.behavior.command.receiver.Editor;

/**
 * 复制命令
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:37:28
 */
public class ReplaceCommand extends AbstractCommand {

    private final String replaceContent;

    public ReplaceCommand(Application application, Editor editor, String replaceContent) {
        super(application, editor);
        this.replaceContent = replaceContent;
    }

    @Override
    public boolean execute() {
        // 将老数据进行备份
        saveBackup();
        // 替换新数据
        editor.setContent(replaceContent);
        return true;
    }

    @Override
    public void undo() {
        // 将编辑器内容恢复
        editor.setContent(backup);
        // 清空备份数据
        cleanBackup();
    }
}
