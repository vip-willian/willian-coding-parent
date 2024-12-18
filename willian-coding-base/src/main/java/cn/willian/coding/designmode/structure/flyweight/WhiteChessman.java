package cn.willian.coding.designmode.structure.flyweight;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 14:28:05
 */
// 白色围棋棋子类: 具体的享元类
public class WhiteChessman implements Chessman {
    @Override
    public String color() {
        return "白色";
    }
}
