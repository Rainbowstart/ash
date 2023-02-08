package com.springcloud.flowableDemo.dao;

import com.springcloud.flowableDemo.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DemoDao {

    List<Map<String, Object>> getRoleDemo();

    List<Map<String, Object>> getRoleListByIdsDemo(@Param(value = "idList") List<String> idList);

    void updateRoleDemo(Role role);
}
