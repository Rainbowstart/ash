package com.springcloud.quartz.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Job实体类
 * @ClassName: ScheduleJob
 */
@Component
@Data
@ConfigurationProperties
public class ScheduleJob {
    /**
     * 正在运行
     */
    public static final String STATUS_RUNNING = "1";
    /**
     *不在运行
     */
    public static final String STATUS_NOT_RUNNING = "0";
    /** 序列化id*/
    private static final long serialVersionUID = -7100490638092503733L;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 是否启动任务
     */
    private String jobStatus;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * spring bean 任务所在 类名
     */
    private String jobClass;
    /**
     * 任务调用的方法传入的参数,统一使用String,
     */
    private Map<String,Object> parameters;
    /**
     * 任务是否有状态 是否支持并行
     */
    private boolean isConcurrent;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 是否更新
     */
    private boolean isUpdateData;
    /**
     * 是否要求唤醒
     */
    private boolean requestsRecovery;
    /**
     * 是否持久化
     */
    private boolean isDruable;
}