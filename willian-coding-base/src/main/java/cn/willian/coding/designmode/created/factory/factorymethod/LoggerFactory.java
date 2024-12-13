package cn.willian.coding.designmode.created.factory.factorymethod;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:36:02
 */
public interface LoggerFactory {

    /**
     * 定义日志处理器
     */
    Logger getLogger();
}
