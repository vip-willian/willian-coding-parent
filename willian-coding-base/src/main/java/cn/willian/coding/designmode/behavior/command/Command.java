package cn.willian.coding.designmode.behavior.command;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:32:36
 */
public interface Command {

    /**
     * 执行命令
     *
     * @return 返回是否记录命令历史 true 保存 / false 不保存
     */
    boolean execute();

    /**
     * 撤销命令
     */
    void undo();
}
