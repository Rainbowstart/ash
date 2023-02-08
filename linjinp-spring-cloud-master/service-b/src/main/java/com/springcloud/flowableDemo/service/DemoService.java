package com.springcloud.flowableDemo.service;

import com.springcloud.flowableDemo.entity.Role;

import java.util.List;
import java.util.Map;

public interface DemoService {

    List<Map<String, Object>> getRoleDemo();

    List<Map<String, Object>> getRoleListByIdsDemo(List<String> idList);

    void updateRoleDemo(Role role);
}
