package cn.willian.coding.seata.user.domain;

import io.mybatis.provider.Entity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:36
 */
@Data
@Entity.Table(value = "user_account_tcc", remark = "用户账户TCC表", autoResultMap = true)
public class UserAccountTCC {

    /**
     * 事务ID
     */
    @Entity.Column(value = "xid", id = true, remark = "事务ID", updatable = false)
    private String xid;
    /**
     * 用户ID
     */
    @Entity.Column(value = "user_id", remark = "用户ID")
    private Long userId;
    /**
     * 冻结金额
     */
    @Entity.Column(value = "freeze_amount", remark = "冻结金额")
    private BigDecimal freezeAmount;
    /**
     * 事务状态
     */
    @Entity.Column(value = "state", remark = "事务状态")
    private Integer state;
}
