package com.springcloud.flowableDemo.service.impl;

import com.springcloud.flowableDemo.dao.DemoDao;
import com.springcloud.flowableDemo.entity.Role;
import com.springcloud.flowableDemo.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("DemoService")
public class DemoServiceImpl implements DemoService {

    @Resource
    private DemoDao demoDao;

    @Override
    public List<Map<String, Object>> getRoleDemo() {
        return demoDao.getRoleDemo();
    }

    @Override
    public List<Map<String, Object>> getRoleListByIdsDemo(List<String> idList) {
        return demoDao.getRoleListByIdsDemo(idList);
    }

    @Override
    public void updateRoleDemo(Role role) {
        demoDao.updateRoleDemo(role);
    }
}
