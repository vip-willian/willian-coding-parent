package cn.wiilian.coding.es.obj;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 酒店数据
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 12:11:55
 */
@Data
public class EsHotel implements PrimaryID {

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
     * 酒店位置
     */
    private GeoPoint location;
    /**
     * 酒店图片
     */
    private String pic;

    public static EsHotel mapping(TbHotel tbHotel) {

        EsHotel esHotel = new EsHotel();
        esHotel.setId(tbHotel.getId());
        esHotel.setName(tbHotel.getName());
        esHotel.setAddress(tbHotel.getAddress());
        esHotel.setPrice(tbHotel.getPrice());
        esHotel.setScore(tbHotel.getScore());
        esHotel.setBrand(tbHotel.getBrand());
        esHotel.setCity(tbHotel.getCity());
        esHotel.setStarName(tbHotel.getStarName());
        esHotel.setBusiness(tbHotel.getBusiness());
        esHotel.setLocation(
            new GeoPoint(Double.parseDouble(tbHotel.getLongitude()), Double.parseDouble(tbHotel.getLatitude())));
        esHotel.setPic(tbHotel.getPic());
        return esHotel;
    }

    @Override
    @JsonIgnore
    public String getPrimaryId() {
        return String.valueOf(getId());
    }
}
