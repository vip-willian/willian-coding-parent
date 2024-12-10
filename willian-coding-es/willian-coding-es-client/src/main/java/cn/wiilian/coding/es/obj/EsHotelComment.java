package cn.wiilian.coding.es.obj;

import lombok.Data;

/**
 * 酒店评论
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-10 10:35:58
 */
@Data
public class EsHotelComment {

    /**
     * 用户名
     */
    private String name;
    /**
     * 评分
     */
    private Short rating;
    /**
     * 评论内容
     */
    private String comment;
}
