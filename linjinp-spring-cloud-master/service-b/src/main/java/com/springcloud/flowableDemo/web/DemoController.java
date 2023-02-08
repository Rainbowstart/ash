package com.springcloud.flowableDemo.web;

import com.springcloud.entity.ErrorMsg;
import com.springcloud.flowableDemo.dao.UserGroupPermissionDao;
import com.springcloud.flowableDemo.dao.UserPermissionDao;
import com.springcloud.flowableDemo.entity.Group;
import com.springcloud.flowableDemo.entity.Role;
import com.springcloud.flowableDemo.entity.User;
import com.springcloud.flowableDemo.service.DemoService;
import com.springcloud.flowableDemo.service.FeignClientService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色权限
 * @author: linjinp
 * @create: 2019-05-07 13:35
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/flowable")
public class DemoController {

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    @Resource
    private DemoService demoService;

    @Resource
    private FeignClientService feignClientService;

    @Resource
    private UserPermissionDao userPermissionDao;

    @Resource
    private UserGroupPermissionDao userGroupPermissionDao;


    /**
     * 获取当前角色数据列表
     * @return
     */
    @GetMapping(value = "/getDataList")
    public ErrorMsg getDataList() {
        ErrorMsg errorMsg = ErrorMsg.SUCCESS;
        // 获取全部数据
        List<Map<String, Object>> roles = demoService.getRoleDemo();
        errorMsg.setErrorMsg("SUCCESS");
        errorMsg.setData(roles);
        return errorMsg;
    }

    /**
     * 发起流程
     * @return
     */
    @GetMapping(value = "/start/{dataId}")
    public ErrorMsg start(HttpServletRequest request, @PathVariable(value = "dataId") String dataId) {
        ErrorMsg errorMsg = ErrorMsg.SUCCESS;
        String token = request.getHeader("X-Token");
        User user = (User) redisTemplate.opsForValue().get(token);
        // 数据与流程进行绑定
        feignClientService.start("Test03:4:62557", user.getUsername(), dataId);
        // 更新审核状态
        Role role = new Role();
        role.setId(Integer.valueOf(dataId));
        role.setStatus(1);
        demoService.updateRoleDemo(role);
        return errorMsg;
    }

    /**
     * 获取审核列表
     * @return
     */
    @GetMapping(value = "/getApproveList")
    public ErrorMsg getApproveList(HttpServletRequest request) {
        ErrorMsg errorMsg = ErrorMsg.SUCCESS;
        String token = request.getHeader("X-Token");
        User user = (User) redisTemplate.opsForValue().get(token);

        // 获取用户拥有的用户组，admin，aaa，test
        List<Group> hasGroup = userPermissionDao.getUserGroup(user.getUsername());
        // 取出组 ID
        List<String> groups = new ArrayList<>();
        hasGroup.forEach(group -> {
            groups.add(group.getId());
        });

        // 调用流程引擎封装的接口
        // 根据用户组获取需要审核的数据对应的流程信息
        // 主要为了满足我设置的是实际组的情况
        List<Map<String, Object>> idListByGroupMapList = feignClientService.getRuntimeBusinessKeyByGroup(groups);

        // 调用流程引擎封装的接口
        // 获取自己发起的正在进行的审核数据对应的流程信息
        List<Map<String, Object>> idListByUserMapList = feignClientService.getRuntimeBusinessKeyByUser(user.getUsername());
        // 整合两个 list
        idListByUserMapList.addAll(idListByGroupMapList);

        // 这里开始对数据进行组合，方便前端展示
        List<String> idList = new ArrayList<>();
        idListByUserMapList.forEach(idListByUserMap -> {
            // businessKey 为业务键，我用来存数据的 ID
            idList.add((String) idListByUserMap.get("businessKey"));
        });
        // 获取正在审核的数据
        List<Map<String, Object>> roles = demoService.getRoleListByIdsDemo(idList);
        // 数据整合，将需要在前端展示的流程信息与业务数据信息组合到一起
        roles.forEach(role -> {
            idListByUserMapList.forEach(idListByUserMap -> {
                if (role.get("id").toString().equals(idListByUserMap.get("businessKey").toString())) {
                    role.put("taskId", idListByUserMap.get("taskId"));
                    role.put("processInstanceName", idListByUserMap.get("processInstanceName"));
                    role.put("startTime", idListByUserMap.get("startTime"));
                }
            });
        });
        // 统一返回
        errorMsg.setData(roles);
        return errorMsg;
    }

    /**
     * 进行审核
     * @param request
     * @param taskId 任务节点 Id
     * @return
     */
    @GetMapping(value = "/task/{taskId}/{dataId}")
    public ErrorMsg taskByAssignee(HttpServletRequest request, @PathVariable(value = "taskId") String taskId, @PathVariable(value = "dataId") String dataId) {
        ErrorMsg errorMsg = ErrorMsg.SUCCESS;
        String token = request.getHeader("X-Token");

        User user = (User) redisTemplate.opsForValue().get(token);

        // 会签情况需要用户列表数据
        List<String> assigneeList = userGroupPermissionDao.getIdByGroup("ce537a73-dbc2-4d2f-ac5f-2cdc208a20e0");
        // 设置会签所需的用户列表数据
        // 会签与监听器会签节点用户组参数
        feignClientService.setListVariable(taskId, "assigneeList", assigneeList);
        // 多实例基数，没用到
        feignClientService.setVariable(taskId, "cycleNum", assigneeList.size());
        // 第二审核人节点，审核人参数
        feignClientService.setVariable(taskId, "reviewer", "bbb");

        // 流程完成所需的条件参数
        // 主要用于第三审核人节点，根据审核人进行分支判断依据
        Map<String, Object> map = new HashMap<>();
        map.put("assignee", user.getUsername());

        // 根据任务节点 Id，获取流程实例 Id
        String processInstanceId = feignClientService.getTaskInfo(taskId);
        // 完成任务，taskId 任务节点 ID
        feignClientService.taskByAssignee(taskId, user.getUsername(), map);
        // 通过流程实例 Id，判断流程是否结束
        boolean isFinish = feignClientService.checkProcessInstanceFinish(processInstanceId);
        if (isFinish) {
            // 更新审核状态
            Role role = new Role();
            role.setId(Integer.valueOf(dataId));
            role.setStatus(2);
            demoService.updateRoleDemo(role);
        }
        return errorMsg;
    }

    /**
     * 获取审核历史记录
     * @return
     */
    @GetMapping(value = "/getApproveHistory")
    public ErrorMsg getApproveHistory(HttpServletRequest request) {
        ErrorMsg errorMsg = ErrorMsg.SUCCESS;
        String token = request.getHeader("X-Token");
        User user = (User) redisTemplate.opsForValue().get(token);

        // 调用流程引擎接口，获取审核人为当前用户的已完成的任务
        List<Map<String, Object>> historys = feignClientService.getHistoryByUser(user.getUsername());
        errorMsg.setData(historys);
        return errorMsg;
    }
}
