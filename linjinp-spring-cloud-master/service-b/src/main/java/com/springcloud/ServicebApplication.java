package com.springcloud;

import com.springcloud.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
@EnableDiscoveryClient
// feign 客户端启动
@EnableFeignClients
@ComponentScan(basePackages = {"com.springcloud.*"})
@MapperScan("com.springcloud.*.dao")
// 由于没数据库，排除配置
@SpringBootApplication
public class ServicebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicebApplication.class, args);
    }
}


