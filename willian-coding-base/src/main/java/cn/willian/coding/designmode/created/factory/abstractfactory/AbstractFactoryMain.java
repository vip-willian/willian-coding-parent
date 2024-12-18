package cn.willian.coding.designmode.created.factory.abstractfactory;

import cn.willian.coding.tools.XmlReadUtils;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:39:07
 */
@SuppressWarnings("all")
public class AbstractFactoryMain {
    public static void main(String[] args) {

        CarFactory factory = (CarFactory) XmlReadUtils.getBean("car-factory.xml","className");

        Engine engine = factory.createEngine();
        engine.display();

        Chassis chassis = factory.createChassis();
        chassis.display();

        Body body = factory.createBody();
        body.display();

        ElectricalSystem electricalSystem = factory.createElectricalSystem();
        electricalSystem.display();
    }
}
