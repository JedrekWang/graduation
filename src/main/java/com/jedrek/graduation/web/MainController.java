package com.jedrek.graduation.web;

import com.alibaba.fastjson.JSONObject;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
    public String index(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return "login";
        }
        Cookie cookie = new Cookie("param"+cookies.length, "logined");
        response.addCookie(cookie);
        return "test";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String show_index(
            @RequestParam("account") String account,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        // todo 通过email给该邮箱发送一封邮件，然后等待用户点击指定链接，若点击了根据账号，邮件，密码创建一个用户

        return "test";

    }
//    FIXME 这是通过axios由前端将数据传给后台
//    @ResponseBody
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public Object index_join(@RequestBody Map map) {
//        System.out.println("username: "+map.get("username"));
//        System.out.println("password: "+map.get("password"));
//        JSONObject json = new JSONObject();
//        json.put("success", true);
//        return json;
//    }


    /**
     * 提供登录功能
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "new_login";
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
        return "new_login";
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

