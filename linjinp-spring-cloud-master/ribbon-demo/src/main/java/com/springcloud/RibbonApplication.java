package com.springcloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.springcloud.exception.GlobalExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

// 开启 Hystrix 断路器
@EnableCircuitBreaker
//启用全局异常拦截器
@Import(value={GlobalExceptionHandler.class})
// Eureka 客户端
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.springcloud.*"})
@MapperScan("com.springcloud.*.dao")
// 由于没数据库，排除配置
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RibbonApplication {

    /**
     * @LoadBalanced 对 RestTemplate 启用 ribbon 负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 指定负载均衡的策略
     * 默认为 ZoneAvoidanceRule，可不写
     * @return
     */
    @Bean
    public IRule myRule() {
        return new RoundRobinRule(); // 显式的指定使用轮询算法
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}


