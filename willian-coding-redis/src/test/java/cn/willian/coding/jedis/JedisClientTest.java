package cn.willian.coding.jedis;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import redis.clients.jedis.JedisPooled;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-26 22:09:08
 */
public class JedisClientTest {

    private JedisPooled jedis;

    @BeforeEach
    public void before() {
        jedis = new JedisPooled("127.0.0.1", 6379, null, "123456");
    }

    @Test
    public void testString() {
        String result = jedis.set("name", "lisi");
        System.out.println("设置key结果 = " + result);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    public void testHash() {
        jedis.hset("user:1", "name", "lisi");
        jedis.hset("user:1", "age", "30");
        jedis.hset("user:1", "sex", "man");

        Map<String, String> map = jedis.hgetAll("user:1");
        map.forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
    }

    @AfterEach
    public void after() {

        if (jedis != null) {
            jedis.close();
        }
    }
}
