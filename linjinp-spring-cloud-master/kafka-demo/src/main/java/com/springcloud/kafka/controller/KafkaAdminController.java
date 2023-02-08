package com.springcloud.kafka.controller;

import com.springcloud.entity.ErrorMsg;
import com.springcloud.kafka.util.KafkaUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Kafka Admin 示例代码
 * @author: linjinp
 * @create: 2020-04-30 14:36
 **/
@RestController
@RequestMapping("/kafka/admin")
public class KafkaAdminController {

    @Resource
    private KafkaUtils kafkaUtils;

    /**
     * 查看 Topic 列表
     * @return
     */
    @GetMapping("/list/topic")
    public ErrorMsg listTopic() {
        AdminClient adminClient = kafkaUtils.createAdminClient();
        ListTopicsResult result = adminClient.listTopics();
        adminClient.close();
        return ErrorMsg.SUCCESS.setNewData(result);
    }
}
