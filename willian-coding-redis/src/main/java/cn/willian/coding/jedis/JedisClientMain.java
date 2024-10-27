package cn.willian.coding.jedis;

import redis.clients.jedis.JedisPooled;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-26 21:31:14
 */
public class JedisClientMain {

    public static void main(String[] args) {
        JedisPooled jedis = new JedisPooled("localhost", 6379, null, "123456");
        jedis.del("age");
        String result = jedis.set("age", "30");
        System.out.println("插入数据结果" + result);


        String age = jedis.get("age");
        System.out.println(age);

        // JedisPool jedisPool = new JedisPool("localhost", 6379);
        //
        // Jedis jedis = jedisPool.getResource();
        //
        // jedis.auth("123456");
        //
        // jedis.get
        jedis.close();
    }
}
