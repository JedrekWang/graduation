package com.jedrek.graduation.entity;

import lombok.Data;

/**
 *  用户的注册信息
 */
@Data
public class LoginUser {
    private String account;
    private String email;
    private String passowrd;
}
