package com.springcloud.feignDemo.service;

import com.springcloud.feignDemo.controller.FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-a", fallbackFactory = FeignFallBack.class)
public interface FeignService {

    @RequestMapping(value = "/getMsg", method = RequestMethod.GET)
    String getMsg();
}
