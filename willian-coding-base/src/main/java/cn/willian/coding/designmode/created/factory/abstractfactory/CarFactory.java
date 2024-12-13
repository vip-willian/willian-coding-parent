package cn.willian.coding.designmode.created.factory.abstractfactory;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:31:45
 */
public interface CarFactory {

    /**
     * 发动机
     */
    Engine createEngine();

    /**
     * 底盘
     */
    Chassis createChassis();

    /**
     * 车身
     */
    Body createBody();

    /**
     * 电子系统
     */
    ElectricalSystem createElectricalSystem();
}
