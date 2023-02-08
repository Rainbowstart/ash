package com.springcloud.flowableDemo.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGroupPermissionDao {

    List<String> getIdByGroup(@Param(value = "groupId") String groupId);
}
