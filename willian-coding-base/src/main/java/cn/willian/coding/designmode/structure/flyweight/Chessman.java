package cn.willian.coding.designmode.structure.flyweight;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 14:28:05
 */
// 围棋棋子类：抽象享元类
public interface Chessman {

    String color();

    default void display() {
        System.out.println("棋子颜色: " + color());
    }
}
