package cn.willian.coding.designmode.created.factory.factorymethod.impl.logger;

import cn.willian.coding.designmode.created.factory.factorymethod.Logger;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:37:14
 */
public class FileLogger implements Logger {

    @Override
    public void debug(String message) {
        System.out.println("【DEBUG日志级别】文件记录日志: " + message);
    }

    @Override
    public void info(String message) {
        System.out.println("【INFO日志级别】文件记录日志: " + message);
    }

    @Override
    public void warn(String message) {
        System.out.println("【WARN日志级别】文件记录日志: " + message);
    }

    @Override
    public void error(String message) {
        System.out.println("【ERROR日志级别】文件记录日志: " + message);
    }
}
