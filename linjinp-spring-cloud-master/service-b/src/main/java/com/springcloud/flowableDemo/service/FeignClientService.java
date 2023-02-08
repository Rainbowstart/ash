package com.springcloud.flowableDemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@FeignClient(name = "flowable-demo")
@FeignClient(name = "flowable-demo2", url = "http://localhost:8184/")
public interface FeignClientService {

    /**
     * 获取正在运行的数据 Id 列表
     * @return
     */
    @RequestMapping(value = "/flowable/api/getRuntimeDataId", method = RequestMethod.GET)
    List<String> getRuntimeDataId();

    /**
     * 启动流程
     *
     * @param deployId 部署的流程 Id，来自 ACT_RE_PROCDEF
     * @param userId   用户 Id
     * @param dataKey  数据 Key，业务键，一般为表单数据的 ID，仅作为表单数据与流程实例关联的依据
     * @return
     */
    @RequestMapping(value = "/flowable/api/start/{deployId}/{userId}/{dataKey}", method = RequestMethod.GET)
    List<String> start(@PathVariable(value = "deployId") String deployId, @PathVariable(value = "userId") String userId, @PathVariable(value = "dataKey") String dataKey);

    /**
     * 任务处理
     *
     * @param taskId   任务 Id，来自 ACT_RU_TASK
     * @param assignee 设置审核人，替换
     * @param map      完成任务需要的条件参数
     * @return
     */
    @RequestMapping(value = "/flowable/api/task", method = RequestMethod.POST)
    List<String> taskByAssignee(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "assignee") String assignee, @RequestBody Map<String, Object> map);

    /**
     * 根据用户，获取需要审核的业务键 business_key 列表
     *
     * @param userId 用户 Id
     * @return
     */
    @RequestMapping(value = "/flowable/api/getRuntimeBusinessKeyByUser/{userId}", method = RequestMethod.GET)
    List<Map<String, Object>> getRuntimeBusinessKeyByUser(@PathVariable(value = "userId") String userId);

    /**
     * 获取组，获取需要审核的业务键 business_key 列表
     *
     * @param groupIds 组 Id
     * @return
     */
    @RequestMapping(value = "/flowable/api/getRuntimeBusinessKeyByGroup", method = RequestMethod.POST)
    List<Map<String, Object>> getRuntimeBusinessKeyByGroup(@RequestBody List<String> groupIds);

    /**
     * 获取用户审核历史
     *
     * @param userId 发起人 Id
     * @return
     */
    @RequestMapping(value = "/flowable/api/getHistoryByUser/{userId}", method = RequestMethod.GET)
    List<Map<String, Object>> getHistoryByUser(@PathVariable(value = "userId") String userId);

    /**
     * 根据任务节点获取流程实例 Id
     *
     * @param taskId 任务节点 Id
     * @return
     */
    @RequestMapping(value = "/flowable/api/getTaskInfo/{taskId}", method = RequestMethod.GET)
    String getTaskInfo(@PathVariable(value = "taskId") String taskId);

    /**
     * 通过流程实例 Id，判断流程是否结束
     *
     * @param processInstanceId 流程实例 Id
     * @return true 结束，false 未结束
     */
    @RequestMapping(value = "/flowable/api/checkProcessInstanceFinish/{processInstanceId}", method = RequestMethod.GET)
    boolean checkProcessInstanceFinish(@PathVariable(value = "processInstanceId") String processInstanceId);

    /**
     * 获取当前候选组
     *
     * @param taskId 任务 Id，来自 ACT_RU_TASK
     * @return
     */
    @RequestMapping(value = "/flowable/api/taskInfo/{taskId}", method = RequestMethod.GET)
    List<String> taskInfo(@PathVariable(value = "taskId") String taskId);

    /**
     * 设置任务参数
     *
     * @param taskId 任务ID
     * @param key 键
     * @param value 值
     * @return
     */
    @RequestMapping(value = "/flowable/api/setVariable", method = RequestMethod.POST)
    void setVariable(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "key") String key, @RequestParam(value = "value") Object value);

    /**
     * 设置任务参数，List 使用
     *
     * @param taskId 任务ID
     * @param key 键
     * @param value 值
     * @return
     */
    @RequestMapping(value = "/flowable/api/setListVariable", method = RequestMethod.POST)
    void setListVariable(@RequestParam(value = "taskId") String taskId, @RequestParam(value = "key") String key, @RequestParam(value = "value") List<String> value);

}
