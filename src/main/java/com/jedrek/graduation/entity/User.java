package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
public class User {
    private Integer userId;   // 自增
    private String userName; //用户姓名
    private String account;  // 账号,用于登录和查找记录
    private String tel;
    private String sex;
    private String email;
    private String school;
    private String userDesc; // 用户一句话自我描述
    private String major;  // 专业
    private String title;    // 职称
    private String research; // 科学研究
    private String paper; // 发表论文
    private String reward; // 奖励荣誉
    private String teachingWork; //教学工作
    private Date createdDate;
    private Date lastLoginDate;

}
