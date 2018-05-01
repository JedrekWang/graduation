package com.jedrek.graduation.service;


import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.mapper.UserGroupConMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.Group;
import java.util.List;

@Service
@Transactional
public class UserGroupConService {

    private UserGroupConMapper userGroupConMapper;

    @Autowired
    public UserGroupConService(UserGroupConMapper userGroupConMapper) {
        this.userGroupConMapper = userGroupConMapper;
    }

    public List<User> queryUsersByGroup(Integer groupId) {
        return userGroupConMapper.queryUsersByGroup(groupId);
    }

    public List<Group> queryGroupByUserId(Integer userId) {
        return userGroupConMapper.queryGroupByUserId(userId);
    }

    public int userAddGroup(Integer userId, Integer groupId) {
        return userGroupConMapper.userAddGroup(userId, groupId);
    }

}
