package cn.willian.coding.designmode.structure.flyweight;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 14:38:27
 */
public class FlyWeightMain {
    public static void main(String[] args) {

        Chessman black1, black2, black3, white1, white2;
        // 获取享元工厂对象
        ChessmanFactory factory = ChessmanFactory.getInstance();

        // 通过享元工厂获取三颗黑子
        black1 = factory.getChessman("black");
        black2 = factory.getChessman("black");
        black3 = factory.getChessman("black");
        System.out.println("判断两颗黑子是否相同：" + (black1 == black2));

        // 通过享元工厂获取两颗白子
        white1 = factory.getChessman("white");
        white2 = factory.getChessman("white");
        System.out.println("判断两颗白子是否相同：" + (white1 == white2));

        // 显示棋子
        black1.display();
        black2.display();
        black3.display();
        white1.display();
        white2.display();
    }
}
