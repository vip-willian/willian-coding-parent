package cn.willian.coding.designmode.created.factory.simplefactory;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-12 20:19:16
 */
public class OrangeProduct implements FruitProduct {

    @Override
    public void desc() {
        System.out.println("我是一个橙子");
    }
}
