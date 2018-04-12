package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.Folder;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.FolderService;
import com.jedrek.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FolderController {

    private FolderService folderService;
    private UserService userService;

    @Autowired
    public FolderController(FolderService folderService, UserService userService) {
        this.folderService = folderService;
        this.userService = userService;
    }

    @RequestMapping("{userName}/rootFolders")
    @ResponseBody
    public Object getRootFolder(@PathVariable String userName) {
        User user = userService.queryUserByAccount(userName);
        List<Folder> folders = folderService.queryRootFolderByUser(user.getUserId());
        return folders;
    }
}
