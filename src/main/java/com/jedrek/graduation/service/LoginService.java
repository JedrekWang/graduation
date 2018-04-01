package com.jedrek.graduation.service;


import com.jedrek.graduation.entity.Login;
import com.jedrek.graduation.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {

    private LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }


    public int saveLogin(Login login) {
        return loginMapper.saveLogin(login);
    }

    public Login queryLoginByAccount(String account) {
        return loginMapper.queryLoginByAccount(account);
    }
//
//    public int deleteLoginUser(String account) {
//        return loginMapper.deleteLoginUser(account);
//    }
//
//    public int updateLoginUser(String account, LoginUser newLoginUser) {
//        return loginMapper.updateLoginUser(account, newLoginUser);
//    }
}
