package cn.willian.coding.designmode.created.factory.factorymethod;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:39:16
 */
public class DatabaseLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger() {
        return new DatabaseLogger();
    }
}
