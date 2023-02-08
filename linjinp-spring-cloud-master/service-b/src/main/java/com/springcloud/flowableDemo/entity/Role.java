package com.springcloud.flowableDemo.entity;

import lombok.Data;

import java.util.Date;

/**
 * 角色信息表
 * @author: linjinp
 * @create: 2019-04-17 17:28
 * @throw
 **/
@Data
public class Role {

    private Integer id;

    private String roleNumber;

    private String roleName;

    private Date insertTime;

    private Date updateTime;

    private Integer isUse;

    private Integer status;

}
