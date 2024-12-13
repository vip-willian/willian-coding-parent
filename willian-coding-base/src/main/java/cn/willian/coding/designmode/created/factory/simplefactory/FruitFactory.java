package cn.willian.coding.designmode.created.factory.simplefactory;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:20:37
 */
public class FruitFactory {

    public static FruitProduct getFruit(String type) {

        switch (type) {
            case "apple":
                return new AppleProduct();
            case "orange":
                return new OrangeProduct();
            default:
                throw new IllegalArgumentException("异常参数");
        }

    }
}
