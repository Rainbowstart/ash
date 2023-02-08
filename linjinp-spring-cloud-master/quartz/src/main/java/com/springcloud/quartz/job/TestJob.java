package com.springcloud.quartz.job;

import com.springcloud.factory.ScheduleJobFactory;
import com.springcloud.quartz.entity.ScheduleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Bean 启动测试示例
 * @author: linjinp
 * @create: 2020-08-19 15:11
 **/
@Slf4j
@Component
public class TestJob extends QuartzJobBean {

    @Autowired
    private ScheduleJobFactory scheduleJobFactory;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        System.out.println("定时任务调用中：" + new Date());
    }

    @Bean
    public boolean startJob() throws Exception {
        ScheduleJob job = new ScheduleJob();
        job.setConcurrent(false);
        // 每秒一次
        job.setCronExpression("0/1 * * * * ?");
        // 任务名
        job.setJobName("quartz_test_job");
        // 分组名
        job.setJobGroup("quartz_test");
        job.setJobStatus("1");
        job.setParameters(null);
        // 被调用任务 class 路径
        job.setJobClass("com.springcloud.quartz.job.TestJob");
        job.setRequestsRecovery(true);
        job.setDruable(true);
        job.setUpdateData(true);
        job.setDescription("定时任务测试测试");
        scheduleJobFactory.initJob(job);
        return true;
    }
}
