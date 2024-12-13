package cn.willian.coding.designmode.created.factory.abstractfactory;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:31:45
 */
public class BMWCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new BMWEngine();
    }

    @Override
    public Chassis createChassis() {
        return new BMWChassis();
    }

    @Override
    public Body createBody() {
        return new BMWBody();
    }

    @Override
    public ElectricalSystem createElectricalSystem() {
        return new BMWElectricalSystem();
    }
}
