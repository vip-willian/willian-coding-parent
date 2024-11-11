package cn.willian.coding.sharingsphere.jdbc;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.willian.coding.sharingsphere.jdbc.utils.DataSourceQueryUtils;
import cn.willian.coding.sharingsphere.jdbc.utils.DataSourceUtils;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-09 16:33:52
 */
public class OrderService {

    public static void main(String[] args) throws Exception {

        // 批量插入
        batchInsert();
    }

    public static void batchInsert() throws Exception {

        DataSource dataSource = DataSourceUtils.getDataSource();
        String sql = "insert into t_order(id, order_id, user_id, order_price) values(?,?,?,?)";
        DataSourceQueryUtils.batchUpdate(dataSource.getConnection(), sql, OrderService::putParameters);
    }

    public static void putParameters(PreparedStatement ps) {

        try {
            for (int i = 0; i < 10; i++) {
                long snowflakeNextId = IdUtil.getSnowflakeNextId();
                ps.setLong(1, snowflakeNextId);
                ps.setLong(2, snowflakeNextId);
                ps.setLong(3, RandomUtil.randomLong(100));
                ps.setBigDecimal(4, RandomUtil.randomBigDecimal(new BigDecimal(100)));
                ps.addBatch();
            }
        } catch (Exception e) {
            System.out.println("设置出错");
        }
    }
}
