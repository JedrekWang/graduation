package com.jedrek.graduation.web;

import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.mapper.UserMapper;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 首页，左边展示网站功能，右边是注册登录功能
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }


    /**
     * 提供登录功能
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        // todo 完成具体的登录验证工作
        return null;
    }

    /**
     * 提供注册功能
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String showJoin() {
        return "join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join() {
        // todo 完成邮箱注册功能
        return null;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews() {
        return "news";
    }

}

