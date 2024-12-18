package cn.willian.coding.designmode.behavior.command.impl;

import cn.willian.coding.designmode.behavior.command.AbstractCommand;
import cn.willian.coding.designmode.behavior.command.receiver.Application;
import cn.willian.coding.designmode.behavior.command.receiver.Editor;

/**
 * 剪切命令
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:39:13
 */
public class CutCommand extends AbstractCommand {

    public CutCommand(Application application, Editor editor) {
        super(application, editor);
    }

    @Override
    public boolean execute() {

        // 先进行备份
        saveBackup();
        // 将编辑器内容放入剪切板上去
        application.setClipboard(editor.getContent());
        // 删除编辑器内容
        editor.deleteSelection();
        return true;
    }

    @Override
    public void undo() {

        // 将编辑器内容恢复
        editor.setContent(backup);
        // 清空备份数据
        cleanBackup();
        // 清空剪切板
        application.setClipboard("");
    }
}
