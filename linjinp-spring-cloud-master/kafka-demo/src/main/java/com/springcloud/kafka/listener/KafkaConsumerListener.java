package com.springcloud.kafka.listener;

import com.alibaba.fastjson.JSONArray;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消费者监听器
 * @author: linjinp
 * @create: 2020-04-30 17:31
 **/
@Component
public class KafkaConsumerListener {

    /**
     * 发送消息
     * @param message 内容
     * @return
     */
    @KafkaListener(topics = "test")
    public void getMessage(String message) {
        byte[] arr = JSONArray.parseObject(message, byte[].class);
        System.out.println(new Date() + "：" + arr);
    }
}
