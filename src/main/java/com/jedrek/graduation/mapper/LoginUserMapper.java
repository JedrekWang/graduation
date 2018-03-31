package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.LoginUser;
import org.apache.ibatis.annotations.Param;

public interface LoginUserMapper {

    int saveLoginUser(LoginUser loginUser);

    LoginUser queryLoginUserByAccount(String account);

    int deleteLoginUser(String account);

    int updateLoginUser(@Param("account") String account, @Param("newLoginUser") LoginUser newLoginUser);
}
