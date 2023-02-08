package com.springcloud.flowableDemo.dao;

import com.springcloud.flowableDemo.entity.Group;

import java.util.List;


public interface UserPermissionDao {

    List<Group> getUserGroup(String username);

}
