package com.jedrek.graduationdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "hello world";
    }

    @RequestMapping("/test")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/news",method = RequestMethod.GET)
    public String getNews() {
        return "news";
    }
}
