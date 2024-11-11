package cn.willian.coding.sharingsphere.jdbc.utils;

import org.apache.commons.collections4.MapUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Consumer;


/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-09 16:16:13
 */
public class DataSourceQueryUtils {

    /**
     * 查询
     */
    public static void query(Connection connection, String sql, Map<Integer, Object> parameters) throws SQLException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            if (MapUtils.isNotEmpty(parameters)) {
                for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                    statement.setObject(entry.getKey(), entry.getValue());
                }
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("user_id"));
                System.out.println(resultSet.getLong("order_id"));
            }
        } finally {
            close(connection, statement, resultSet);
        }
    }

    /**
     * 更新
     */
    public static void update(Connection connection, String sql, Map<Integer, Object> parameters) throws SQLException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            if (MapUtils.isNotEmpty(parameters)) {
                for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
                    statement.setObject(entry.getKey(), entry.getValue());
                }
            }
            statement.executeUpdate();
        } finally {
            close(connection, statement, null);
        }
    }

    /**
     * 更新
     */
    public static void batchUpdate(Connection connection, String sql, Consumer<PreparedStatement> consumer) throws SQLException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            consumer.accept(statement);
            statement.executeBatch();
            connection.commit();
        } finally {
            close(connection, statement, null);
        }
    }

    private static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("连接关闭失败");
        }
    }
}
