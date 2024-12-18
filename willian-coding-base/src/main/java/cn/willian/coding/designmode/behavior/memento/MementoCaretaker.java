package cn.willian.coding.designmode.behavior.memento;

import java.util.Stack;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 22:26:34
 */
@Data
public class MementoCaretaker {

    private Stack<ChessmanMemento> stack;

    public MementoCaretaker() {
        this.stack = new Stack<>();
    }

    public void put(ChessmanMemento chessmanMemento) {
        stack.push(chessmanMemento);
    }

    public ChessmanMemento pop() {
        return stack.pop();
    }

    // private ChessmanMemento memento;
}
