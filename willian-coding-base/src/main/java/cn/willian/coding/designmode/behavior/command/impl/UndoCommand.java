package cn.willian.coding.designmode.behavior.command.impl;

import cn.willian.coding.designmode.behavior.command.AbstractCommand;
import cn.willian.coding.designmode.behavior.command.receiver.Application;
import cn.willian.coding.designmode.behavior.command.receiver.Editor;

/**
 * 撤销命令
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:59:32
 */
public class UndoCommand extends AbstractCommand {

    public UndoCommand(Application application, Editor editor) {
        super(application, editor);
    }

    @Override
    public boolean execute() {

        application.undo();
        return false;
    }

    @Override
    public void undo() {

    }
}
