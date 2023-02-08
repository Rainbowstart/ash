package com.springcloud;


import com.springcloud.exception.GlobalExceptionHandler;
import com.springcloud.flowable.conf.AppDispatcherServletConfiguration;
import com.springcloud.flowable.conf.ApplicationConfiguration;
import com.springcloud.flowable.conf.DatabaseAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@Import(value={GlobalExceptionHandler.class,
        // 引入修改的配置
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class,
        // 引入 DatabaseConfiguration 表更新转换
//        DatabaseConfiguration.class
        // 引入 DatabaseConfiguration 表更新转换
        DatabaseAutoConfiguration.class
})
// Eureka 客户端
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.springcloud.*"})
@MapperScan("com.springcloud.*.dao")
// 移除 Security 自动配置
// Spring Cloud 为 Finchley 版本
//@SpringBootApplication(exclude={SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
// Spring Cloud 为 Greenwich 版本
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FlowableApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }
}
//启用全局异常拦截器


