package cn.wiilian.coding.es.obj;

import java.io.Serializable;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 10:27:53
 */
@Data
public class EsUser implements Serializable {

    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 电话
     */
    private String phone;
    /**
     * 地址
     */
    private EsAddress address;
}
