package com.jedrek.graduation.web;


import com.jedrek.graduation.entity.LoginUser;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.LoginUserService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.DESUtil;
import com.jedrek.graduation.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserController {
    private UserService userService;
    private LoginUserService loginUserService;

    @Autowired
    public UserController(UserService userService, LoginUserService loginUserService) {
        this.userService = userService;
        this.loginUserService = loginUserService;
    }


    /**
     *  验证用户点击的链接与数据库存放数据是否相同
     * @param email
     * @param hash
     * @return
     */
    @RequestMapping("verify_email")
    public String verifyEmail(@RequestParam("email") String email, @RequestParam("hash") String hash) {
        String message = MailUtil.decryptHashCode(hash);
        String[] userMessage = message.split("_");
        if (userMessage.length == 3) {
            String account = userMessage[0];
            String password = userMessage[2];
            LoginUser loginUser = loginUserService.queryLoginUserByAccount(account);
            if (loginUser != null && Objects.equals(loginUser.getEmail(), email) &&
                    Objects.equals(loginUser.getPassowrd(), password)) {
                return "test";  // 认证成功返回首页
            }
        }
        return "error";
    }
    /**
     * 查找指定用户的首页
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{userName}", method = RequestMethod.GET)
    public String getUserMessage(@PathVariable String userName) {
//        User user = userService.queryUserById(userId);
//        String name = user.getUserName();
//        return name;
        return null;
    }
    /**
     * 修改指定用户的信息
     * @param userName
     * @return
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
