package cn.willian.coding.designmode.structure.facade;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 13:58:21
 */
public class FacadeMain {
    public static void main(String[] args) {

        ShoppingFacade shoppingFacade = new ShoppingFacade();

        // 购买商品
        shoppingFacade.buy("iPhone16",8000);
        System.out.println("------------------------------------");
        // 取消购买
        shoppingFacade.cancel("iPhone16",8000);
    }
}
