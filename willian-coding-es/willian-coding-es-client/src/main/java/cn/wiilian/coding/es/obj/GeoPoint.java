package cn.wiilian.coding.es.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 14:47:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoPoint {

    /**
     * 经度
     */
    private double lon;
    /**
     * 纬度
     */
    private double lat;
}
