package com.springcloud;

import com.springcloud.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
// Eureka 客户端
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.springcloud.*"})
@MapperScan("com.springcloud.*.dao")
// 去除身份验证
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ActivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }
}


