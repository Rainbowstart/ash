package com.springcloud.flowableDemo.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户表
 * @author: linjinp
 * @create: 2019-04-16 16:32
 * @throw
 **/
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String fullName;

    private Date insertTime;

    private Date updateTime;

    private Integer isUse;

    private String salt;

    private String email;

    private String phone;

    private String deptNumber;

    private String deptName;

    private String content;

    private String number;

    List<Role> role;

    List<String> button;
}