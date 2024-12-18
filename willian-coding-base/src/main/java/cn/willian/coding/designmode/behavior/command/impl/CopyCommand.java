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
public class CopyCommand extends AbstractCommand {

    public CopyCommand(Application application, Editor editor) {
        super(application, editor);
    }

    @Override
    public boolean execute() {
        // 复制命令不会被保存到历史记录中，因为它没有改变编辑器的状态。
        application.setClipboard(editor.getContent());
        return false;
    }

    @Override
    public void undo() {
        // 复制命令撤销，将剪切板内容清空
        application.setClipboard("");
    }
}
