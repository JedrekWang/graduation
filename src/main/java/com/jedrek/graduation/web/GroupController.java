package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.UserGroup;
import com.jedrek.graduation.service.UserGroupConService;
import com.jedrek.graduation.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.acl.Group;
import java.util.List;

@Controller
public class GroupController {
    private UserGroupService userGroupService;
    private UserGroupConService userGroupConService;

    @Autowired
    public GroupController(UserGroupService userGroupService, UserGroupConService userGroupConService) {
        this.userGroupService = userGroupService;
        this.userGroupConService = userGroupConService;
    }

    @RequestMapping("/groupName/{groupId}")
    @ResponseBody
    public String getGroup(@PathVariable Integer groupId) {
        UserGroup userGroup = userGroupService.queryGroupById(groupId);
        return userGroup.getGroupName();
    }

    @ResponseBody
    @RequestMapping("/groups")
    public Object getGroups() {
        List<UserGroup> groups = userGroupService.queryAll();
        return groups;
    }

    @RequestMapping("/groups/{groupId}")
    public String showGroup(@PathVariable Integer groupId) {
        return "group";
    }

    @RequestMapping("manageGroup")
    public String showManageGroup() {
        return "admin_group";
    }


    @ResponseBody
    @RequestMapping("/allGroup/{userId}")
    public Object getAllGroupByUserId(@PathVariable Integer userId) {
        List<Group> groups = userGroupConService.queryGroupByUserId(userId);
        return groups;
    }
}
