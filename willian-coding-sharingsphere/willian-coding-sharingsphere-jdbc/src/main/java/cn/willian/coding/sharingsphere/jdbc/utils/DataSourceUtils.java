package cn.willian.coding.sharingsphere.jdbc.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-09 15:30:09
 */
public class DataSourceUtils {

    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "root";
    private static final String PASS = "userOfWillian";

    public static DataSource getDataSource(String name) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(String.format("jdbc:mysql://localhost:3306/%s?useSSL=false&serverTimezone=UTC", name));
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    private static Map<String, DataSource> getDataSourceMap() {

        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("db_goods_0", getDataSource("db_goods_0"));
        dataSourceMap.put("db_goods_1", getDataSource("db_goods_1"));
        return dataSourceMap;
    }

    public static DataSource getDataSource() throws SQLException {

        return ShardingSphereDataSourceFactory.createDataSource(getDataSourceMap(), getShardingRuleConfiguration(), new Properties());
    }

    private static Collection<RuleConfiguration> getShardingRuleConfiguration() {

        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();

        // 设置分库
        configuration.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
        configuration.getShardingAlgorithms().putAll(getDbShardingAlgorithm());

        // 设置order表分表
        configuration.getTables().add(getOrderShardingTableRules());
        configuration.getShardingAlgorithms().putAll(getOrderTableShardingAlgorithm());
        return Collections.singleton(configuration);
    }

    private static Map<String, ShardingSphereAlgorithmConfiguration> getDbShardingAlgorithm() {

        // 参考配置：https://shardingsphere.apache.org/document/current/cn/user-manual/common-config/builtin-algorithm/sharding/
        // 配置分库算法
        Map<String, ShardingSphereAlgorithmConfiguration> result = new HashMap<>();
        Properties properties = new Properties();
        properties.setProperty("algorithm-expression", "db_goods_${user_id % 2}");
        result.put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", properties));
        return result;
    }

    private static Map<String, ShardingSphereAlgorithmConfiguration> getOrderTableShardingAlgorithm() {

        Map<String, ShardingSphereAlgorithmConfiguration> result = new HashMap<>();
        Properties properties = new Properties();
        properties.setProperty("algorithm-expression", "t_order_${order_id % 2}");
        result.put("orderTableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", properties));
        return result;
    }

    private static ShardingTableRuleConfiguration getOrderShardingTableRules() {

        ShardingTableRuleConfiguration orderTableRuleConfiguration = new ShardingTableRuleConfiguration("t_order", "db_goods_${0..1}.t_order_${0..1}");
        orderTableRuleConfiguration.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        orderTableRuleConfiguration.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "orderTableShardingAlgorithm"));
        return orderTableRuleConfiguration;
    }
}
