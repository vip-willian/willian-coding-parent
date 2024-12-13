package cn.willian.coding.designmode.created.factory.abstractfactory;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 21:31:45
 */
public class BYDCarFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new BYDEngine();
    }

    @Override
    public Chassis createChassis() {
        return new BYDChassis();
    }

    @Override
    public Body createBody() {
        return new BYDBody();
    }

    @Override
    public ElectricalSystem createElectricalSystem() {
        return new BYDElectricalSystem();
    }
}
