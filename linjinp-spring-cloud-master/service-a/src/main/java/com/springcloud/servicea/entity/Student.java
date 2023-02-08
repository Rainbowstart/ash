package com.springcloud.servicea.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 学生类
 * @author: linjinp
 * @create: 2019-07-02 14:19
 **/
@Component
@ConfigurationProperties(prefix = "student")
@Data
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private String content;
}