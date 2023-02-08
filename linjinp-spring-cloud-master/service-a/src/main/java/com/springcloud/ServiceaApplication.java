package com.springcloud;

import com.springcloud.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
// Eureka 客户端
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.springcloud.*"})
@MapperScan("com.springcloud.*.dao")
@SpringBootApplication
public class ServiceaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceaApplication.class, args);
    }
}


