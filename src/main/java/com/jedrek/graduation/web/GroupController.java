package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.entity.UserGroup;
import com.jedrek.graduation.service.UserGroupConService;
import com.jedrek.graduation.service.UserGroupService;
import com.jedrek.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.acl.Group;
import java.util.List;
import java.util.Map;

@Controller
public class GroupController {
    private UserGroupService userGroupService;
    private UserGroupConService userGroupConService;
    private UserService userService;

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
    @RequestMapping(value = "userTestGroup/{groupId}", method = RequestMethod.GET)
    public Object getGroupMessage(@PathVariable Integer groupId) {
        UserGroup userGroup = userGroupService.queryGroupById(groupId);
        return userGroup;
    }


    @ResponseBody
    @RequestMapping("groupAllUser/{groupId}")
    public Object getAllUserFromGroup(@PathVariable Integer groupId) {
        List<User> users = userGroupConService.queryUsersByGroup(groupId);
        return users;
    }

    @ResponseBody
    @RequestMapping("/allGroup/{userId}")
    public Object getAllGroupByUserId(@PathVariable Integer userId) {
        List<Group> groups = userGroupConService.queryGroupByUserId(userId);
        return groups;
    }

    @ResponseBody
    @RequestMapping(value = "userGroup/", method = RequestMethod.POST)
    public Object addGroup(
            @RequestBody Map map) {
        String groupName = (String)map.get("groupName");
        String groupDesc = (String)map.get("groupDesc");
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName(groupName);
        userGroup.setGroupDesc(groupDesc);
        int i = userGroupService.addGroup(userGroup);
        if(i > 0) {
            return "success";
        }
        return "error";
    }
}
