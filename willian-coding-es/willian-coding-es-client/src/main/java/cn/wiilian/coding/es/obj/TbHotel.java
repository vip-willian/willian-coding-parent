package cn.wiilian.coding.es.obj;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 13:54:09
 */
@Data
public class TbHotel {

    /**
     * 酒店ID
     */
    private Long id;
    /**
     * 酒店名称
     */
    private String name;
    /**
     * 酒店地址
     */
    private String address;
    /**
     * 酒店价格
     */
    private Integer price;
    /**
     * 酒店评分
     */
    private Integer score;
    /**
     * 酒店品牌
     */
    private String brand;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 酒店星级，1星到5星，1钻到5钻
     */
    private String starName;
    /**
     * 商圈
     */
    private String business;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 酒店图片
     */
    private String pic;
}
