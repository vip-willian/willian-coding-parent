package cn.willian.coding.designmode.created.factory.factorymethod;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:22:48
 */
@SuppressWarnings("all")
public class FactoryMethodMain {

    public static void main(String[] args) {
        LoggerFactory factory = (LoggerFactory)getBean();
        Logger logger = factory.getLogger();

        logger.info("当前用户信息");
        logger.error("出现错误");
    }

    public static Object getBean() {

        try (InputStream inputStream = FactoryMethodMain.class.getClassLoader().getResourceAsStream("custom-log.xml")) {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            Element classNameElement = root.element("className");
            String className = classNameElement.getText();
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            return new ConsoleLoggerFactory();
        }
    }
}
