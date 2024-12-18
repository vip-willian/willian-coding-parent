package cn.willian.coding.tools;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:22:51
 */
public class XmlReadUtils {

    public static Object getBean(String fileName,String elementKey) {

        try (InputStream inputStream = XmlReadUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            Element classNameElement = root.element(elementKey);
            String className = classNameElement.getText();
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }
}
