package com.jedrek.graduation.web;


import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.entity.Login;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.LoginService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.CookieUtil;
import com.jedrek.graduation.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
            Login loginUser = new Login();
            loginUser.setAccount(account);
            loginUser.setEmail(email);
            loginUser.setPassword(password);
            loginService.saveLogin(loginUser);
//            Login login = loginService.queryLoginByAccount(account);
//            if (login != null && Objects.equals(login.getEmail(), email) &&
//                    Objects.equals(login.getPassword(), password)) {
            Cookie cookie = new Cookie("isVerify", "true");
            Cookie cookie1 = new Cookie("currentUser", account);
            response.addCookie(cookie);
            response.addCookie(cookie1);
            return "redirect:/login";  // 认证成功返回登录页

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

    @RequestMapping("admin")
    public String showAdmin(HttpServletRequest request) {
        String currentUser = CookieUtil.getCookieValue(request, "currentUser");
        if (Objects.equals(currentUser, "admin")) {
            return "admin";
        } else {
            return "error";
        }
    }


    @RequestMapping("upload_user")
    public String showCreateUser() {
        return "create_user";
    }

    /**
     * 新建用户
     * @param request
     * @param userName
     * @param userDesc
     * @param sex
     * @param school
     * @param tel
     * @param groupId
     * @return
     */
    @RequestMapping(value = "upload_user", method = RequestMethod.POST)
    public String uploadUserMessage(
            HttpServletRequest request,
            @RequestParam String userName,
            @RequestParam String userDesc,
            @RequestParam String sex,
            @RequestParam String school,
            @RequestParam String tel,
            @RequestParam Integer groupId,
            @RequestParam MultipartFile file) throws IOException {
        String account = CookieUtil.getCookieValue(request, "currentUser");
        Login login = loginService.queryLoginByAccount(account);
        String email = login.getEmail();
        User user = new User();
        user.setUserName(userName);
        user.setAccount(account);
        user.setEmail(email);
        user.setUserDesc(userDesc);
        user.setSchool(school);
        user.setSex(sex);
        user.setTel(tel);
        int i = userService.addUser(user);
        if (i > 0) {
            // 将用户上传的头像存入文件系统
            String filename = file.getOriginalFilename();
            String[] split = filename.split("\\.", 2);
            String headerUploadPath = Constant.headerPath + account + "."+ split[1];
            File uploadFile = new File(headerUploadPath);
            File parentFile = uploadFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            uploadFile.createNewFile();
            file.transferTo(uploadFile);
            return "redirect:/";
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("users")
    public Object getAllUsers() {
        List<User> users = userService.queryAllUser();
        return users;
    }


    @ResponseBody
    @RequestMapping("group/{groupId}")
    public Object getUsersByGroup(@PathVariable Integer groupId) {
        List<User> userByGroup = userService.queryUserByGroup(groupId);
        return userByGroup;
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
