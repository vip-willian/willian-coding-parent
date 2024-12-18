package cn.willian.coding.designmode.behavior.chain;

import lombok.Setter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 15:53:26
 */
@Setter
public abstract class AbstractAuditor implements Auditor {

    /**
     * 审批人姓名
     */
    protected String name;
    /**
     * 下一个审批人
     */
    protected Auditor nextAuditor;

    public AbstractAuditor(String name) {
        this.name = name;
    }
}
