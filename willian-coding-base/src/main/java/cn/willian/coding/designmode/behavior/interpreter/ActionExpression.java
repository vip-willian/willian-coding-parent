package cn.willian.coding.designmode.behavior.interpreter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 16:37:28
 */
// 动作解释：终结符表达式
public class ActionExpression implements Expression {

    private final String action;

    public ActionExpression(String action) {
        this.action = action;
    }

    @Override
    public String interpret() {

        if (action.equalsIgnoreCase("move")) {
            return "移动";
        }
        if (action.equalsIgnoreCase("run")) {
            return "闪现";
        }
        return "无效指令";
    }
}
