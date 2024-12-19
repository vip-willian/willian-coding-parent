package cn.willian.coding.designmode.structure.bridge.impl.database;

import cn.willian.coding.designmode.structure.bridge.AbstractDatabase;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-19 17:35:58
 */
// RefinedAbstraction（扩充抽象类）
public class RedisDatabase extends AbstractDatabase {

    @Override
    public String read() {

        System.out.println("从Redis数据库读取内容");
        return "你好 Redis";
    }
}
