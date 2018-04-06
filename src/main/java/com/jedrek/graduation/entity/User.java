package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User {
    private Integer userId;   // 自增
    private Integer groupId; // 用户所在的小组
    private String userName; //用户姓名
    private String account;
    private String tel;
    private String sex;
    private String email;
    private String birth;
    private String school;
    private String userDesc; // 用户一句话自我描述
    private Integer authorityTableId;
    private Date createdDate;
    private Date lastLoginDate;

}
