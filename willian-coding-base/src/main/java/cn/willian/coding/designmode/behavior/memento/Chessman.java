package cn.willian.coding.designmode.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 象棋棋子类
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 22:22:37
 */
@Data
@AllArgsConstructor
public class Chessman {

    private String label;
    private Integer x;
    private Integer y;

    // 保存备忘录
    public ChessmanMemento save() {
        return new ChessmanMemento(label, x, y);
    }

    // 恢复状态
    public void restore(ChessmanMemento memento) {
        if (memento != null) {
            this.label = memento.getLabel();
            this.x = memento.getX();
            this.y = memento.getY();
        }
    }
}
