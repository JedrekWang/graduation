package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    int saveLogin(Login login);

    Login queryLoginByAccount(String account);
//
//    int deleteLoginUser(String account);

//    int updateLoginUser(@Param("account") String account, @Param("newLoginUser") LoginUser newLoginUser);
}
