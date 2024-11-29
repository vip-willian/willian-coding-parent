package cn.willian.coding.redis.limiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-11-28 22:15:58
 */
@SpringBootApplication
public class RedisLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLimiterApplication.class, args);
    }
}
