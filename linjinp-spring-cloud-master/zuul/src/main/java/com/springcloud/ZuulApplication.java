package com.springcloud;

import com.springcloud.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

// zuul
@EnableZuulProxy
//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
// Eureka 客户端
@EnableDiscoveryClient
// 由于没数据库，排除配置
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    // 正则表达式指定Zuul的路由匹配规则
//    @Bean
//    public PatternServiceRouteMapper serviceRouteMapper() {
//        // 调⽤构造函数PatternServiceRouteMapper(String servicePattern, String routePattern)
//
//        // servicePattern指定微服务的正则
//        // routePattern指定路由正则
//        // 最后一个 "-" 后面以 v 打头的为 version，之前的都是 name
//        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
//    }

}


