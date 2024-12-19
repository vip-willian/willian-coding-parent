package cn.willian.coding.designmode.structure.bridge;

import cn.willian.coding.designmode.structure.bridge.impl.database.MysqlDatabase;
import cn.willian.coding.designmode.structure.bridge.impl.database.OracleDatabase;
import cn.willian.coding.designmode.structure.bridge.impl.database.RedisDatabase;
import cn.willian.coding.designmode.structure.bridge.impl.format.HtmlConverter;
import cn.willian.coding.designmode.structure.bridge.impl.format.JsonConverter;
import cn.willian.coding.designmode.structure.bridge.impl.format.TxtConverter;
import cn.willian.coding.designmode.structure.bridge.impl.format.XmlConverter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:27:31
 */
public class BridgeMain {

    public static void main(String[] args) {

        System.out.println("****组合1: Redis+文本****");
        Converter txtConverter = new TxtConverter();
        Database redisDatabase = new RedisDatabase();
        redisDatabase.setConverter(txtConverter);
        System.out.println("输出内容: " + redisDatabase.input());

        System.out.println("****组合2: Mysql+JSON****");
        Converter jsonConverter = new JsonConverter();
        Database mysqlDatabase = new MysqlDatabase();
        mysqlDatabase.setConverter(jsonConverter);
        System.out.println("输出内容: " + mysqlDatabase.input());

        System.out.println("****组合3: Oracle+HTML****");
        Converter htmlConverter = new HtmlConverter();
        Database oracleDatabase = new OracleDatabase();
        oracleDatabase.setConverter(htmlConverter);
        System.out.println("输出内容: " + oracleDatabase.input());

        System.out.println("****组合4: Mysql+XML****");
        Converter xmlConverter = new XmlConverter();
        mysqlDatabase.setConverter(xmlConverter);
        System.out.println("输出内容: " + mysqlDatabase.input());
    }
}
