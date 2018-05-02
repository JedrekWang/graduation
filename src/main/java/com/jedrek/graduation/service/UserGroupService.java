package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.UserGroup;
import com.jedrek.graduation.mapper.UserGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserGroupService {

    private UserGroupMapper userGroupMapper;

    @Autowired
    public UserGroupService(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    public UserGroup queryGroupById(Integer groupId) {
        return userGroupMapper.queryGroupById(groupId);
    }

    public int saveGroup(UserGroup userGroup) {
        return userGroupMapper.saveGroup(userGroup);
    }

    public int deleteGroup(Integer groupId) {
        return userGroupMapper.deleteGroup(groupId);
    }

    public List<UserGroup> queryAll() {
        return userGroupMapper.queryAll();
    }

    public int addGroup(UserGroup group) {
        return userGroupMapper.addGroup(group);
    }

}
