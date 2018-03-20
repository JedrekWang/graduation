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

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "hello world";
    }

    @RequestMapping("/test")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews() {
        return "news";
    }

    @RequestMapping(value = "/document/upload", method = RequestMethod.GET)
    public String showUploadDocument() {
        return "upload";
    }

    @ResponseBody
    @RequestMapping(value = "document/upload", method = RequestMethod.POST)
    public String uploadDocument(MultipartFile file) {
        // todo 简单的上传文件，需要添加路径的散列算法以及文件名的修改
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String uploadDate = df.format(day);


        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            String uploadPath = Constant.uploadPath + name;
            try {
                file.transferTo(new File(uploadPath));
                return "success";
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "failed";

    }

    @ResponseBody
    @RequestMapping(value = "document/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDocument(
            @RequestParam String filename) throws Exception {

        String filePath = Constant.uploadPath + filename;
        // fixme 用户上传的不应该是文件名，而是文档的编号，通过编号查找数据库得到url，再与uploadPath结合并下载
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        headers.setContentDispositionFormData("attachment", downloadFielName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping("user/{userId}")
    public String getUserMessage(@PathVariable Integer userId) {
        User user = userService.queryUserById(userId);
        String name = user.getUserName();
        return name;
    }
}

