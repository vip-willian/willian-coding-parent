package cn.willian.coding.redis;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.willian.coding.redis.dto.UserDTO;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-26 23:04:01
 */
@SpringBootTest
public class SpringDataRedisApplicationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testString() {
        // 默认采用JDK序列化方式
        redisTemplate.opsForValue().set("name", "王炎");
        Object result = redisTemplate.opsForValue().get("student");
        System.out.println(result);
    }

    @Test
    public void testObj() {
        redisTemplate.opsForValue().set("user:100", new UserDTO(2L, "15857108029", "张三"));
        UserDTO user = (UserDTO) redisTemplate.opsForValue().get("user：100");
        System.out.println("user = " + user);
    }

    @Test
    public void testStringTemplate() throws JsonProcessingException {
        UserDTO user = new UserDTO(1L, "15857108029", "张三");
        stringRedisTemplate.opsForValue().set("user:200", objectMapper.writeValueAsString(user));
        String result = stringRedisTemplate.opsForValue().get("user:200");
        System.out.println("user = " + objectMapper.readValue(result, UserDTO.class));
    }

    @Test
    public void testHash() {
        stringRedisTemplate.opsForHash().put("user:4434", "name", "350");
        stringRedisTemplate.opsForHash().put("user:4434", "age", "33");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:4434");
        System.out.println("entries = " + entries);
    }

    @Test
    public void testList() {

    }
}
