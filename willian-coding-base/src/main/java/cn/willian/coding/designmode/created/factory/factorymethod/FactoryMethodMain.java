package cn.willian.coding.designmode.created.factory.factorymethod;

import cn.willian.coding.tools.XmlReadUtils;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:22:48
 */
@SuppressWarnings("all")
public class FactoryMethodMain {

    public static void main(String[] args) {
        LoggerFactory factory = (LoggerFactory) XmlReadUtils.getBean("custom-log.xml","className");
        Logger logger = factory.getLogger();

        logger.info("当前用户信息");
        logger.error("出现错误");
    }
}
