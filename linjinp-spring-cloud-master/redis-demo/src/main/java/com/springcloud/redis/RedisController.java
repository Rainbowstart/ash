package com.springcloud.redis;

import com.springcloud.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Redis 测试
 * @author: linjinp
 * @create: 2020-04-03 10:46
 **/
@RequestMapping("/")
@RestController
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/set/{key}/{value}")
    public boolean set(@PathVariable String key, @PathVariable String value) {
        return redisUtil.set(key, value);
    }

    @GetMapping("/get/{key}")
    public <T> T get(@PathVariable String key) {
        return (T) redisUtil.get(key);
    }
}
