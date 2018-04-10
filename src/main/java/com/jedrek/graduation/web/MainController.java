package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.Login;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.LoginService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
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
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookie.getName(), "isVerify") && Objects.equals(cookie.getValue(), "true")) {
                    return "test";
                }
            }
        }
        return "join";
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
//        System.out.println("username: "+map.get("account"));
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
        return "sign_in";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("account") String account,
            @RequestParam("password")  String password) {
        Login login = loginService.queryLoginByAccount(account);
        String loginPassword = login.getPassword();
        if (Objects.equals(loginPassword, password)) {
            User user = userService.queryUserByAccount(account);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", account);
                Cookie cookie = new Cookie("isVerify", "true");
                Cookie cookie2 = new Cookie("currentUser", account);
                response.addCookie(cookie);
                response.addCookie(cookie2);
                return "redirect:/";
            }
        }
        return "error";
    }
}

