package cn.willian.coding.designmode.behavior.memento;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 22:27:36
 */
public class MementoMain {
    public static void main(String[] args) {

        MementoCaretaker mc = new MementoCaretaker();
        Chessman chess = new Chessman("车", 1, 1);
        display(chess);
        mc.put(chess.save()); // 保存状态

        chess.setY(4);
        display(chess);
        mc.put(chess.save()); // 保存状态

        chess.setX(5);
        display(chess);
        mc.put(chess.save()); // 保存状态

        chess.setY(8);
        display(chess);

        System.out.println("******悔棋******");

        chess.restore(mc.pop()); // 恢复状态
        display(chess);

        chess.restore(mc.pop()); // 恢复状态
        display(chess);

        chess.restore(mc.pop()); // 恢复状态
        display(chess);
    }

    public static void display(Chessman chess) {
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }
}
