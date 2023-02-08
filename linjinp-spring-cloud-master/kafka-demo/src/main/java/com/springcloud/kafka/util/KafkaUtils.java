package com.springcloud.kafka.util;

import lombok.Data;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Kafka 工具类
 * @author: linjinp
 * @create: 2020-04-30 14:51
 **/
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class KafkaUtils {

    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> bootstrapServers;

    public AdminClient createAdminClient() {
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }
}
