package cn.willian.coding.seata.order.domain;

import io.mybatis.provider.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:36
 */
@Data
@Entity.Table(value = "`order`", remark = "订单表", autoResultMap = true)
public class Order {

    /**
     * 订单ID
     */
    @Entity.Column(value = "id", id = true, remark = "订单ID", updatable = false)
    private Long id;
    /**
     * 用户ID -> 购买用户
     */
    @Entity.Column(value = "user_id", remark = "用户ID")
    private Long userId;
    /**
     * 订单总价
     */
    @Entity.Column(value = "total_price", remark = "订单总价")
    private BigDecimal totalPrice;
    /**
     * 订单状态
     */
    @Entity.Column(value = "order_status", remark = "订单状态")
    private Integer orderStatus;
    /**
     * 支付时间
     */
    @Entity.Column(value = "pay_time", remark = "支付时间")
    private Date payTime;
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
