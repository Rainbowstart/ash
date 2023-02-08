package com.springcloud.ribbonDemo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon 使用示例
 * @author: linjinp
 * @create: 2019-08-16 15:56
 **/
@RestController
@RequestMapping(value = "/")
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取信息示例
     * @return
     */
    // Hystrix 自定义参数使用示例
    @HystrixCommand(
            fallbackMethod = "fallback",
            commandProperties = {
                    // 方法执行超时时间，默认值是 1000，即 1 秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="10000"),
                    // 熔断降级时间配置
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value="10000"),
            },
            threadPoolProperties = {
                    // 核心线程池的大小
                    @HystrixProperty(name = "coreSize", value = "1"),
                    // 设置队列大小为 5，默认为 -1 不启用队列
                    @HystrixProperty(name = "maxQueueSize", value = "1")
            }
    )
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMsg(@PathVariable("id") Integer id) throws InterruptedException {
        Thread.sleep(2000);
        return restTemplate.getForObject("http://service-a/getMsg", String.class);
    }

    /**
     * 降级逻辑
     * 也可以当作错误返回
     * @return
     */
    public String fallback(Integer id, Throwable throwable) {
        System.out.println("id ======" + id);
        System.out.println("throwable ======" + throwable);
        return "fallback";
    }
}
