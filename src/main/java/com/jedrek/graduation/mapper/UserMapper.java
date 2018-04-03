package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(User user);

    int deleteUser(Integer userId);

    int modifyUser(@Param("userId") Integer userId, @Param("newUser") User newUser);

    User queryUser(Integer userId);

    User queryUserByAccount(String account);

    List<User> queryUserByGroup(Integer groupId);

    List<User> queryUserBySchool(String school);

    List<User> queryAllUser();

}
