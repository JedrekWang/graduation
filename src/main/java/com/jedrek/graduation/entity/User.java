package com.jedrek.graduation.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User {
    private Integer userId;
    private Integer groupId; // 用户所在的小组
    private String userName;
    private String account;
    private String tel;
    private String sex;
    private String email;
    private String birth;
    private String school;
    private Integer authorityTableId;
    private Date createdDate;
    private Date lastLoginDate;

}
