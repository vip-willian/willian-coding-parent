package cn.willian.coding.designmode.structure.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 14:28:05
 */

// 围棋棋子工厂类：享元工厂类，使用单例模式进行设计
public class ChessmanFactory {

    private static volatile ChessmanFactory instance;
    private static Map<String, Chessman> chessmanMap;

    private ChessmanFactory() {
        chessmanMap = new HashMap<>();
        Chessman black, white;
        black = new BlackChessman();
        white = new WhiteChessman();
        chessmanMap.put("black", black);
        chessmanMap.put("white", white);
    }

    // 通过key来获取存储在Hashtable中的享元对象
    public Chessman getChessman(String color) {
        return chessmanMap.get(color);
    }

    @SuppressWarnings("all")
    public static ChessmanFactory getInstance() {
        if (instance == null) {
            synchronized (ChessmanFactory.class) {
                if (instance == null) {
                    instance = new ChessmanFactory();
                }
            }
        }
        return instance;
    }
}