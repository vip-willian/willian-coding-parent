package cn.willian.coding.designmode.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

// 私有访问
/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 22:23:47
 */
@Data
@AllArgsConstructor
public class ChessmanMemento {

    private String label;
    private Integer x;
    private Integer y;
}
