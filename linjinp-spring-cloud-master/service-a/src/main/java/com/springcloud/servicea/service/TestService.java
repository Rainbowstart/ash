package com.springcloud.servicea.service;

import com.springcloud.servicea.entity.Student;

public interface TestService {
    Student getStudent();

    Student updateAge(Integer age);
}
