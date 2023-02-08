package com.springcloud.flowableDemo.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户数据组
 * @author: linjinp
 * @create: 2019-5-24 16:27
 * @throw
 **/
@Data
public class Group {
    private String id;

    private String groupName;

    private String content;

    private Integer isUse;

}