package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.Login;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.mapper.LoginMapper;
import com.jedrek.graduation.service.LoginService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class MainController {

    private UserService userService;
    private LoginService loginService;

    @Autowired
    public MainController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    /**
     * 首页，左边展示网站功能，右边是注册登录功能
     * @return
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), "isVerify") && Objects.equals(cookie.getValue(), "true")) {
                // todo 这里是登录或注册成功后进入的主页
                // todo 需要获取用户的信息，用户文档的所有信息(有分页)
                return "test";
            }

        }
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String show_index(
            @RequestParam("account") String account,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        Login loginUser = new Login();
        loginUser.setAccount(account);
        loginUser.setEmail(email);
        loginUser.setPassword(password);
        loginService.saveLogin(loginUser);
        String data = String.format("%s+%s+%s",account,email,password);
        // 向指定用户发送一封邮件
        MailUtil.sendMail(email, data);
        return "verify_email";

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

