package com.springcloud;

import com.springcloud.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Import;

//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
// Eureka 服务端
@EnableEurekaServer
// 由于没数据库，排除配置
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}


