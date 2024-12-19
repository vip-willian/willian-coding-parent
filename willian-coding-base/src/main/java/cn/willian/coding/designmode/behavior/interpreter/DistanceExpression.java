package cn.willian.coding.designmode.behavior.interpreter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 16:36:58
 */
// 距离解释：终结符表达式
public class DistanceExpression implements Expression {

    private final String distance;

    public DistanceExpression(String distance) {
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return distance;
    }
}
