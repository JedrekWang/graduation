package com.jedrek.graduation.web;


import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
