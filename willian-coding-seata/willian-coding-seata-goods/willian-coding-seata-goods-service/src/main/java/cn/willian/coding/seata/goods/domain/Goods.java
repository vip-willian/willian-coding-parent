package cn.willian.coding.seata.goods.domain;

import io.mybatis.provider.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 09:29:36
 */
@Data
@Entity.Table(value = "goods", remark = "商品表", autoResultMap = true)
public class Goods {

    /**
     * 主键ID
     */
    @Entity.Column(value = "id", id = true, remark = "主键ID", updatable = false)
    private Long id;
    /**
     * 商品类型
     */
    @Entity.Column(value = "goods_type", remark = "商品类型")
    private Integer goodsType;
    /**
     * 商品名称
     */
    @Entity.Column(value = "goods_name", remark = "商品名称")
    private String goodsName;
    /**
     * 商品价格
     */
    @Entity.Column(value = "goods_price", remark = "商品价格")
    private BigDecimal goodsPrice;
    /**
     * 商品库存
     */
    @Entity.Column(value = "goods_inventory", remark = "商品库存")
    private Integer goodsInventory;
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
