package com.jedrek.graduation.dao;

import com.jedrek.graduation.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int addUser(User user);

    int deleteUser(Integer userId);

    int modifyUser(User oldUser, User newUser);

    User queryUser(Integer userId);

    List<User> queryUserByGroup(Integer groupId);

    List<User> queryUserBySchool(String school);

    List<User> queryAllUser();

}
