package cn.willian.coding.seata.user.domain;

import io.mybatis.provider.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:36
 */
@Data
@Entity.Table(value = "user_account", remark = "用户账户表", autoResultMap = true)
public class UserAccount {

    /**
     * 主键ID
     */
    @Entity.Column(value = "id", id = true, remark = "主键ID", updatable = false)
    private Long id;
    /**
     * 用户ID
     */
    @Entity.Column(value = "user_id", remark = "用户ID")
    private Long userId;
    /**
     * 账号金额
     */
    @Entity.Column(value = "amount", remark = "账号金额")
    private BigDecimal amount;
    /**
     * 创建时间
     */
    @Entity.Column(value = "create_time", remark = "支付时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @Entity.Column(value = "update_time", remark = "更新时间")
    private Date updateTime;
}
