package cn.willian.coding.designmode.structure.flyweight;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 14:28:05
 */
// 黑色围棋棋子类: 具体的享元类
public class BlackChessman implements Chessman {
    @Override
    public String color() {
        return "黑色";
    }
}
