package cn.willian.coding.designmode.structure.proxy;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:25:38
 */
public class ProxyMain {

    public static void main(String[] args) {
        LandLordServiceImpl landlord = new LandLordServiceImpl("Willian");
        LandLordServiceProxy proxy = new LandLordServiceProxy("小明", landlord);
        proxy.receiveMoney("Jack", 3000d);
    }
}
