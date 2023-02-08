package com.springcloud.servicea.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * service-b 接口
 * @author: linjinp
 * @create: 2019-07-02 14:16
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/")
public class ServicebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String say() {
        return "hellow world!!!!";
    }

    /**
     * feign 客户端调用
     * @return
     */
    @RequestMapping(value = "/getMsg", method = RequestMethod.GET)
    public String getMsg() {
        return "这里是服务 b";
    }


}
