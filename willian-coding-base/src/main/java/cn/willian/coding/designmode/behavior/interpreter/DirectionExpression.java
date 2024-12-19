package cn.willian.coding.designmode.behavior.interpreter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 16:36:13
 */
// 方向解释：终结符表达式
public class DirectionExpression implements Expression {

    private final String direction;

    public DirectionExpression(String direction) {
        this.direction = direction;
    }

    @Override
    public String interpret() {
        if (direction.equalsIgnoreCase("up")) {
            return "向上";
        }
        if (direction.equalsIgnoreCase("down")) {
            return "向下";
        }
        if (direction.equalsIgnoreCase("left")) {
            return "向左";
        }
        if (direction.equalsIgnoreCase("right")) {
            return "向右";
        }
        return "无效指令";
    }
}
