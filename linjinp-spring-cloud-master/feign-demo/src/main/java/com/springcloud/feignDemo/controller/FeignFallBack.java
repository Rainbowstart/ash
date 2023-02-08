package com.springcloud.feignDemo.controller;

import com.springcloud.feignDemo.service.FeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * feign 异常拦截处理
 * @author: linjinp
 * @create: 2019-08-16 17:21
 **/
@Component
public class FeignFallBack implements FallbackFactory<FeignService> {

    @Override
    public FeignService create(Throwable throwable) {
        return () -> "请求失败了！！！！！！！！！！！！！！";
    }

}
