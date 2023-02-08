package com.springcloud;

import com.springcloud.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

// Feign 启动
@EnableFeignClients
//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
// Eureka 客户端
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.springcloud.*"})
@MapperScan("com.springcloud.*.dao")
// 由于没数据库，排除配置
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}


