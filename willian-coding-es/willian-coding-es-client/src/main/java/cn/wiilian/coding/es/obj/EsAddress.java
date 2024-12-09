package cn.wiilian.coding.es.obj;

import java.io.Serializable;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 10:27:53
 */
@Data
public class EsAddress implements Serializable {

    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址
     */
    private String detail;
}
