package cn.wiilian.coding.es;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.wiilian.coding.es.constants.IndexConstants;
import cn.wiilian.coding.es.obj.EsHotel;
import cn.wiilian.coding.es.obj.TbHotel;
import cn.wiilian.coding.es.tools.EsClientUtils;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-09 13:53:15
 */
public class EsHotelService {

    // 加载hotel酒店数据
    public static void main(String[] args) throws Exception {

        // 从数据库加载全部数据
        List<TbHotel> tbHotels = Db.use().findAll(Entity.create("tb_hotel"), TbHotel.class);

        // 创建索引
        InputStream inputStream = EsHotelService.class.getClassLoader().getResourceAsStream("hotel_mapping.json");
        EsClientUtils.updateIndex(IndexConstants.HOTEL_INDEX, inputStream);

        // 导入数据
        List<EsHotel> esHotels = getEsHotel(tbHotels);
        EsClientUtils.batchUpdateDoc(IndexConstants.HOTEL_ALIAS, esHotels);
    }

    private static List<EsHotel> getEsHotel(List<TbHotel> tbHotels) {

        return tbHotels.stream().map(EsHotel::mapping).collect(Collectors.toList());
    }
}
