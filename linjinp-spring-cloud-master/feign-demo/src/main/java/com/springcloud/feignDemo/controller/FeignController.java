package com.springcloud.feignDemo.controller;

import com.springcloud.feignDemo.service.FeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Feign 使用示例
 * @author: linjinp
 * @create: 2019-08-16 15:56
 **/
@RestController
@RequestMapping(value = "/")
public class FeignController {

    @Resource
    private FeignService feignService;

    /**
     * 获取信息示例
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMsg() {
        return feignService.getMsg();
    }
}
