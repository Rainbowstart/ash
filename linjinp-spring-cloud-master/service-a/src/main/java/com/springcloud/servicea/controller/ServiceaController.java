package com.springcloud.servicea.controller;

import com.springcloud.servicea.entity.Student;
import com.springcloud.servicea.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * service-a 接口
 * @author: linjinp
 * @create: 2019-07-02 14:16
 **/
@RefreshScope
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/")
public class ServiceaController {

    @Resource
    private Student student;

    @Value("${name}")
    private String name;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    @Resource
    private TestService testService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String say() {
        return content;
    }

    @RequestMapping(value = "/api/1", method = RequestMethod.GET)
    public String api() {
        return "访问了 /api/1";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String stu() {
        return student.getName();
    }

    @RequestMapping(value = "/num/{id}", method = RequestMethod.GET)
    public String num(@PathVariable("id") Integer id) {
        return "id = " + id;
    }

    @GetMapping(value = "/num2")
    public String num2(@RequestParam(value = "id", defaultValue = "0", required = false) Integer id) {
        return "id = " + id;
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    public Student getStudent() {
        return testService.getStudent();
    }

    @RequestMapping(value = "/updateAge/{age}", method = RequestMethod.GET)
    public Student updateAge(@PathVariable("age") Integer age) {
        return testService.updateAge(age);
    }

    /**
     * feign 客户端调用
     * @return
     */
    @RequestMapping(value = "/getMsg", method = RequestMethod.GET)
    public String getMsg() {
        return "这里是服务 a";
    }

}
