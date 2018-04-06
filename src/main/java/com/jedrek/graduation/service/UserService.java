package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User queryUserById(Integer userId) {
        return userMapper.queryUser(userId);
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    public int modifyUser(Integer userId, User newUser) {
        return userMapper.modifyUser(userId, newUser);
    }

    public User queryUser(Integer userId) {
        return userMapper.queryUser(userId);
    }

    public User queryUserByAccount(String account) {
        return userMapper.queryUserByAccount(account);
    }

    public List<User> queryUserByGroup(Integer groupId) {
        return userMapper.queryUserByGroup(groupId);
    }

    public List<User> queryUserBySchool(String school) {
        return userMapper.queryUserBySchool(school);
    }

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }



}
