package com.jedrek.graduation.web;

import com.jedrek.graduation.entity.Folder;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.FolderService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class FolderController {

    private FolderService folderService;
    private UserService userService;

    @Autowired
    public FolderController(FolderService folderService, UserService userService) {
        this.folderService = folderService;
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "{userName}/rootFolders", method = RequestMethod.GET)
    public Object getRootFolder(@PathVariable String userName) {
        User user = userService.queryUserByAccount(userName);
        List<Folder> folders = folderService.queryRootFolderByUser(user.getUserId());
        return folders;
    }

    //    @ResponseBody
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public Object index_join(@RequestBody Map map) {
//        System.out.println("username: "+map.get("account"));
//        System.out.println("password: "+map.get("password"));
//        JSONObject json = new JSONObject();
//        json.put("success", true);
//        return json;
//    }
    @ResponseBody
    @RequestMapping(value = "{userName}/folder", method = RequestMethod.POST)
    public Object submitFolder(@PathVariable String userName, @RequestBody Map map) {
        String folderName = (String)map.get("folderName");
        String folderDesc = (String)map.get("folderDesc");
        Integer createdUserId = (Integer)map.get("createdUserId");
        Integer parentFolderId = (Integer)map.get("parentFolderId");
        Folder folder = new Folder();
        folder.setFolderName(folderName);
        folder.setFolderDesc(folderDesc);
        folder.setCreatedUserId(createdUserId);
        folder.setParentFolderId(parentFolderId);
        int i = folderService.addFolder(folder);
        if (i > 0) {
            Folder queryFolder = folderService.queryFolder(parentFolderId, folderName);
            return queryFolder.getFolderId();
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping("folders/{parentFolderId}")
    public Object getSubFolder(@PathVariable Integer parentFolderId) {
        List<Folder> folders = folderService.querySubFolder(parentFolderId);
        return folders;
    }

    @ResponseBody
    @RequestMapping("folders/null")
    public Object getSubFolder(HttpServletRequest request) {
        String currentUser = CookieUtil.getCookieValue(request, "currentUser");
        User user = userService.queryUserByAccount(currentUser);
        List<Folder> folders = folderService.queryRootFolderByUser(user.getUserId());
        return folders;
    }

    @ResponseBody
    @RequestMapping("folder/{folderId}")
    public Object getFolderMessage(@PathVariable Integer folderId) {
        Folder folder = folderService.queryFolderById(folderId);
        return folder;
    }

    @ResponseBody
    @RequestMapping("folder/null")
    public Object getFolderMessage2() {
        return "error";
    }

}
