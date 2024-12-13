package cn.willian.coding.designmode.created.factory.abstractfactory;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.willian.coding.designmode.created.factory.factorymethod.ConsoleLoggerFactory;
import cn.willian.coding.designmode.created.factory.factorymethod.FactoryMethodMain;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:39:07
 */
@SuppressWarnings("all")
public class AbstractFactoryMain {
    public static void main(String[] args) {

        CarFactory factory = (CarFactory)getBean();

        Engine engine = factory.createEngine();
        engine.display();

        Chassis chassis = factory.createChassis();
        chassis.display();

        Body body = factory.createBody();
        body.display();

        ElectricalSystem electricalSystem = factory.createElectricalSystem();
        electricalSystem.display();
    }

    public static Object getBean() {

        try (InputStream inputStream = FactoryMethodMain.class.getClassLoader().getResourceAsStream("car-factory.xml")) {
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
