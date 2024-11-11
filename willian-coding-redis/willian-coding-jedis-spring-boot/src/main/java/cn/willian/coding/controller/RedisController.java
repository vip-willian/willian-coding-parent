package cn.willian.coding.controller;

import cn.willian.coding.util.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-07 15:46:47
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping(value = "/isExist")
    public boolean isExist(@RequestParam(value = "key") String key) {

        return redisUtil.isExists(key);
    }
}
