package com.jedrek.graduation.mapper;


import com.jedrek.graduation.entity.UserGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupMapper {

    UserGroup queryGroupById(Integer groupId);

    int saveGroup(UserGroup userGroup);

    int deleteGroup(Integer groupId);

    List<UserGroup> queryAll();

    int addGroup(UserGroup userGroup);
}
