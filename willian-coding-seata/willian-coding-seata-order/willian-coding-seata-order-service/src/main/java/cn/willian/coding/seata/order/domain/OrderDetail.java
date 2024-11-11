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
@Entity.Table(value = "order_detail", remark = "订单详情表", autoResultMap = true)
public class OrderDetail {

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
     * 订单ID
     */
    @Entity.Column(value = "order_id", remark = "订单ID")
    private Long orderId;
    /**
     * 商品ID
     */
    @Entity.Column(value = "goods_id", remark = "商品ID")
    private Long goodsId;
    /**
     * 购买单价
     */
    @Entity.Column(value = "unit_price", remark = "购买单价")
    private BigDecimal unitPrice;
    /**
     * 购买数量
     */
    @Entity.Column(value = "quantity", remark = "购买数量")
    private Integer quantity;
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
