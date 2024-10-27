package cn.willian.coding.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.willian.coding.tools.Randoms;
import lombok.extern.slf4j.Slf4j;

/**
 * create table db_user.user (
 * <p>
 * id bigint(20) not null primary key auto_increment comment '主键ID',
 * <p>
 * id_card varchar(18) not null comment '身份证号',
 * <p>
 * name varchar(100) not null comment '姓名',
 * <p>
 * age tinyint(4) unsigned not null comment '年龄',
 * <p>
 * height int(4) unsigned not null comment '身高',
 * <p>
 * address varchar(500) not null comment '地址'
 * <p>
 * )comment = '用户表' ENGINE = InnoDB
 * <p>
 * DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;
 *
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-06 09:19:16
 */
@Slf4j
@SuppressWarnings("all")
public class BatchInsertData {

    private static final int batchSize = 1000;
    private static final int totalSize = 50;

    public static void main(String[] args) {

        BatchInsertData batchInsertData = new BatchInsertData();
        batchInsertData.testJDBCBatchInsertUser();
    }

    public void testJDBCBatchInsertUser() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String databaseURL = "jdbc:mysql://localhost:3306/db_user";
        String user = "root";
        String password = "userOfWillian";
        try {
            connection = DriverManager.getConnection(databaseURL, user, password);
            // 关闭自动提交事务，改为手动提交
            connection.setAutoCommit(false);
            log.info("===== 开始插入数据 =====");
            long startTime = System.currentTimeMillis();
            String insertSql = "insert into user(id_card,name,age,height,address) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            int size = totalSize * 10000;
            for (int i = 1; i <= size; i++) {
                String idCard = Randoms.getIdCard();
                preparedStatement.setString(1, idCard);
                preparedStatement.setString(2, Randoms.getName());
                preparedStatement.setInt(3, Randoms.getSex(idCard));
                preparedStatement.setInt(4, Randoms.getHeight());
                preparedStatement.setString(5, Randoms.getAddress());
                // 添加到批处理中
                preparedStatement.addBatch();
                if (i % batchSize == 0) {
                    // 每1000条数据提交一次
                    preparedStatement.executeBatch();
                    connection.commit();
                    log.info("成功插入第 {} 条数据", i);
                }
            }
            // 处理剩余的数据
            preparedStatement.executeBatch();
            connection.commit();
            long spendTime = System.currentTimeMillis() - startTime;
            log.info("成功插入 {} 万条数据,耗时：{} 毫秒", totalSize, spendTime);
        } catch (SQLException e) {
            log.info("error:{}", e.getMessage());
        } finally {
            closeConnection(preparedStatement, connection);
        }
    }

    private void closeConnection(PreparedStatement preparedStatement, Connection connection) {

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
