package cn.willian.coding.designmode.created.factory.factorymethod.impl.factory;

import cn.willian.coding.designmode.created.factory.factorymethod.Logger;
import cn.willian.coding.designmode.created.factory.factorymethod.LoggerFactory;
import cn.willian.coding.designmode.created.factory.factorymethod.impl.logger.FileLogger;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:39:16
 */
public class FileLoggerFactory implements LoggerFactory {

    @Override
    public Logger getLogger() {
        return new FileLogger();
    }
}
