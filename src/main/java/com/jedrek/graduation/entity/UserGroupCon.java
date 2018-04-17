package com.jedrek.graduation.entity;

import lombok.Data;

/**
 * 用户和小组关联类
 */
@Data
public class UserGroupCon {
    private Integer UserGroupConId;
    private Integer userId;
    private Integer groupId;
}
