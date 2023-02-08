package com.springcloud.servicea.dao;

import com.springcloud.servicea.entity.Student;

public interface TestDao {
    Student getStudent();

    void updateAge(Integer age);
}
