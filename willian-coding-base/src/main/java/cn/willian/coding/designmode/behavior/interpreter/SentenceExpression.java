package cn.willian.coding.designmode.behavior.interpreter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 16:19:38
 */
// 简单句子解释：非终结符表达式
public class SentenceExpression implements Expression {

    // 方向
    private final Expression direction;
    // 动作
    private final Expression action;
    // 距离
    private final Expression distance;

    public SentenceExpression(Expression direction, Expression action, Expression distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return direction.interpret() + action.interpret() + distance.interpret();
    }
}
