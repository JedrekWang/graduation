package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.security.acl.Group;
import java.util.List;

@Mapper
public interface UserGroupConMapper {

    List<User> queryUsersByGroup(Integer groupId);

    List<Group> queryGroupByUserId(Integer userId);

    int userAddGroup(
            @Param("userId") Integer userId,
            @Param("groupId") Integer groupId);

}
