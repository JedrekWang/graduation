package com.jedrek.graduation.web;


import com.jedrek.graduation.entity.Login;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.LoginService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class UserController {
    private UserService userService;
    private LoginService loginService;

    @Autowired
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }


    /**
     *  验证用户点击的链接与数据库存放数据是否相同
     * @param email
     * @param hash
     * @return
     */
    @RequestMapping("verify_email")
    public String verifyEmail(
            HttpServletResponse response,
            @RequestParam("email") String email,
            @RequestParam("hash") String hash) {
        String message = MailUtil.decryptHashCode(hash);
        String[] userMessage = message.split("\\+");
        if (userMessage.length == 3) {
            String account = userMessage[0];
            String password = userMessage[2];
            Login login = loginService.queryLoginByAccount(account);
            if (login != null && Objects.equals(login.getEmail(), email) &&
                    Objects.equals(login.getPassword(), password)) {
                Cookie cookie = new Cookie("isVerify", "true");
                Cookie cookie1 = new Cookie("currentUser", account);
                response.addCookie(cookie);
                response.addCookie(cookie1);
                return "redirect:/";  // 认证成功返回首页
            }
        }
        return "error";
    }
    /**
     * 查找指定用户的首页
     * @param account
     * @return
     */
    @RequestMapping(value = "{account}", method = RequestMethod.GET)
    public Object showUserMessage(@PathVariable String account) {
        return "user";
    }

    @ResponseBody
    @RequestMapping("account/{account}")
    public Object getUserMessage(@PathVariable String account) {
        User user = userService.queryUserByAccount(account);
        return user;
    }

    @ResponseBody
    @RequestMapping("login/{account}")
    public Object getUserLoginMessage(@PathVariable String account) {
        Login login = loginService.queryLoginByAccount(account);
        return login;
    }
    /**
     * 修改指定用户的信息
     * @param userName
     * @return
     * @deprecated
     */
    @RequestMapping(value = "{userName}", method = RequestMethod.POST)
    public String updateUserMessage(@PathVariable String userName) {
        return null;
    }

    /**
     * 显示用户的指定tab页
     * @param userName
     * @param tab
     * @return
     */
    @RequestMapping(value = "{userName}/", method = RequestMethod.GET)
    public String showUserTab(@PathVariable String userName,
                              @RequestParam String tab,
                              @RequestParam String q,
                              @RequestParam String type) {
        return null;
    }

    /**
     * 全局搜索指定的文档名
     * @param userName
     * @param q
     * @return
     */
    @RequestMapping(value = "{userName}/search", method = RequestMethod.GET)
    public String searchDocument(@PathVariable String userName, @RequestParam String q) {
        return null;
    }





}
