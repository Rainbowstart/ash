package com.springcloud.quartz.web;

import com.springcloud.factory.ScheduleJobFactory;
import com.springcloud.quartz.entity.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 任务调度接口示例
 * @ClassName: QuartzController
 */
@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private ScheduleJobFactory scheduleJobFactory;

    @RequestMapping("/addjob")
    public void addJob(){
        ScheduleJob job = new ScheduleJob();
        job.setConcurrent(false);
        job.setCronExpression("0/4 * * * * ?");
        job.setJobName("MyJob");
        job.setJobGroup("MyJob_Group");
        job.setJobStatus("1");
        job.setParameters(null);
        job.setJobClass("com.troila.unicorn.task.quartz.MyJob");
        job.setRequestsRecovery(true);
        job.setDruable(true);
        job.setUpdateData(true);
        job.setDescription("54645654646464.");
        try {
            scheduleJobFactory.initJob(job);
            System.out.println("添加job成功!");
        } catch (Exception e) {
            System.out.println("添加job异常!");
            e.printStackTrace();
        }
    }


    @RequestMapping("/stopjobbyname")
    public void stopJobByName(){
        scheduleJobFactory.stopJob("testJob", "DEFAULT");
        System.out.println("成功停止job");
        return;
    }

    @RequestMapping("/restartjob")
    public void restartJob(){
        scheduleJobFactory.restartJob("testJob", "DEFAULT");
        System.out.println("恢复job");
        return;
    }

    @RequestMapping("/deletejob")
    public void deleteJob(){
        scheduleJobFactory.deleteJob("MyJob","MyJob_Group");
    }

    /**
     * 暂停所有job
     */
    @RequestMapping("/pauseall")
    public void stopAll(){
        scheduleJobFactory.pauseAll();
        System.out.println("暂停所有job,重启项目不启动");
        return;
    }

    /**
     * 关闭所有 job
     */
    @RequestMapping("/shutdownall")
    public void shutdownAll(){
        scheduleJobFactory.shutdown();
        System.out.println("已经关闭所有job,重启项目会启动");
        return;
    }

    /**
     * 恢复所有job
     */
    @RequestMapping("/stratall")
    public void restartAll(){
        scheduleJobFactory.restartAll();
        System.out.println("恢复所有job");
        return;
    }
}
