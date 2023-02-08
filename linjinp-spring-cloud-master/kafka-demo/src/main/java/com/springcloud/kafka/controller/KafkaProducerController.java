package com.springcloud.kafka.controller;

import com.alibaba.fastjson.JSONArray;
import com.springcloud.entity.ErrorMsg;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: linjinp
 * @create: 2020-04-30 16:47
 **/
@RestController
@RequestMapping("/kafka/producer")
public class KafkaProducerController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送消息
     * @return
     */
    @GetMapping("/send")
    public ErrorMsg send() {
        byte[] arr = new byte[10];
        kafkaTemplate.send("test", JSONArray.toJSONString(arr));
        return ErrorMsg.SUCCESS;
    }
}
