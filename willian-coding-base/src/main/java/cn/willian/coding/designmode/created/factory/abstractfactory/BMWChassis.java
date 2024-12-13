package cn.willian.coding.designmode.created.factory.abstractfactory;

/**
 * 底盘（Chassis）：是汽车的基础结构，包括驾驶舱、发动机、悬挂系统、制动系统等部分。底盘的主要功能是支撑整个汽车，提供稳定的行驶基础，并保证驾驶员和乘客的安全。
 *
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:28:14
 */
public class BMWChassis implements Chassis {
    @Override
    public void display() {
        System.out.println("BMW Chassis");
    }
}
