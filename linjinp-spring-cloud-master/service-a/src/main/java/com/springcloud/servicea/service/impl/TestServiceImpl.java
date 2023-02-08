package com.springcloud.servicea.service.impl;

import com.springcloud.servicea.dao.TestDao;
import com.springcloud.servicea.entity.Student;
import com.springcloud.servicea.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("TestService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public Student getStudent() {
        return testDao.getStudent();
    }

    @Override
    public Student updateAge(Integer age) {
        testDao.updateAge(age);
        return testDao.getStudent();
    }
}
