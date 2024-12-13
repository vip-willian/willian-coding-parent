package cn.willian.coding.designmode.created.factory.abstractfactory;

/**
 * 电子系统（Electrical System）： 是汽车的各种电子设备和控制系统的总称，包括发动机控制系统、制动系统、悬挂系统、照明系统、通讯系统等。 电子系统的主要功能是控制汽车的各个部件协调工作，提高行驶安全性和舒适性，降低能耗。
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:28:47
 */
public class BYDElectricalSystem implements ElectricalSystem {

    @Override
    public void display() {
        System.out.println("BYD ElectricalSystem");
    }
}
