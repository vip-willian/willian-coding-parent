package cn.willian.coding.designmode.created.factory.simplefactory;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:22:48
 */
public class SimpleFactoryMain {

    public static void main(String[] args) {

        FruitProduct apple = FruitFactory.getFruit("apple");
        apple.desc();

        FruitProduct orange = FruitFactory.getFruit("orange");
        orange.desc();
    }
}
