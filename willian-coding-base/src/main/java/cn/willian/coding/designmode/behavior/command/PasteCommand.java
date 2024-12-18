package cn.willian.coding.designmode.behavior.command;

/**
 * 粘贴命令
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:37:28
 */
public class PasteCommand extends AbstractCommand {

    public PasteCommand(Application application, Editor editor) {
        super(application, editor);
    }

    @Override
    public boolean execute() {

        // 先进行备份
        saveBackup();
        // 剪切板内容放入到编辑器中
        editor.insert(application.getClipboard());
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
