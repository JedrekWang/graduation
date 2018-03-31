package com.jedrek.graduation.service;


import com.jedrek.graduation.entity.LoginUser;
import com.jedrek.graduation.mapper.LoginUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginUserService {

    private LoginUserMapper loginUserMapper;

    @Autowired
    public LoginUserService(LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }


    public int saveLoginUser(LoginUser loginUser) {
        return loginUserMapper.saveLoginUser(loginUser);
    }

    public LoginUser queryLoginUserByAccount(String account) {
        return loginUserMapper.queryLoginUserByAccount(account);
    }

    public int deleteLoginUser(String account) {
        return loginUserMapper.deleteLoginUser(account);
    }

    public int updateLoginUser(String account, LoginUser newLoginUser) {
        return loginUserMapper.updateLoginUser(account, newLoginUser);
    }
}
