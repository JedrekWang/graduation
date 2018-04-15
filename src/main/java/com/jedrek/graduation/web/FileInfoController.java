package com.jedrek.graduation.web;

import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.entity.FileInfo;
import com.jedrek.graduation.entity.Folder;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.FileInfoService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.CookieUtil;
import com.jedrek.graduation.utils.DocumentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FileInfoController {

    private FileInfoService fileInfoService;
    private UserService userService;

    @Autowired
    public FileInfoController(FileInfoService fileInfoService, UserService userService) {
        this.fileInfoService = fileInfoService;
        this.userService = userService;
    }

    @RequestMapping("{userName}/file/{fileId}")
    public String showDoucment(
            @PathVariable String userName,
            @PathVariable Integer fileId) {
        return "fileInfo";
    }

    @ResponseBody
    @RequestMapping("fileInfo/{fileId}")
    public Object getFileInfo(@PathVariable Integer fileId) {
        FileInfo fileInfo = fileInfoService.queryFileByFileId(fileId);
        Date createdDate = fileInfo.getCreatedUserDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日发布");
        String dateString = formatter.format(createdDate);
        Integer userId = fileInfo.getCreatedUserId();
        User user = userService.queryUserById(userId);
        String contentUrl = fileInfo.getContentUrl();
        String finalContentUrl = contentUrl.split("\\.", 2)[0];
        String finalPath = Constant.documentPath + finalContentUrl +".pdf";
        Map ans = new HashMap();
        ans.put("user", user);
        ans.put("fileInfo", fileInfo);
        ans.put("finalPath", finalPath);
        ans.put("dateString", dateString);
        return ans;
    }


    @RequestMapping(value = "file/test", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(
            HttpServletRequest request,
            @RequestParam Integer parentFolderId,
            @RequestBody MultipartFile file) throws IOException {
        System.out.println("test");
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String[] split = filename.split("\\.", 2);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setParentFolderId(parentFolderId);
            fileInfo.setFileName(split[0]);
            fileInfo.setFormat(split[1]);
            String currentUser = CookieUtil.getCookieValue(request, "currentUser");
            if (currentUser != null) {
                User user = userService.queryUserByAccount(currentUser);
                fileInfo.setCreatedUserId(user.getUserId());
                String path = DocumentUtil.handleDocumentPath(filename, new Date());
                fileInfo.setContentUrl(path);
                fileInfoService.addFile(fileInfo);
                String uploadPath = Constant.uploadPath + path;
                File uploadFile = new File(uploadPath);
                File parentFile = uploadFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                uploadFile.createNewFile();
                file.transferTo(uploadFile);
                if (Objects.equals(filename.split("\\.")[1], "docx")) {
                    DocumentUtil.wordToPdf(uploadFile);
                }
                return "code and message";
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("{userName}/rootFiles")
    public Object getRootFiles(@PathVariable String userName) {
        User user = userService.queryUserByAccount(userName);
        List<FileInfo> fileInfos = fileInfoService.queryRootFileByUserId(user.getUserId());
        return fileInfos;
    }

    @ResponseBody
    @RequestMapping("{userName}/parentFolder/{parentFolderId}")
    public Object getParentFolderFiles(
            @PathVariable String userName,
            @PathVariable Integer parentFolderId) {
        User user = userService.queryUserByAccount(userName);
        List<FileInfo> fileInfos = fileInfoService.queryFilesByUserAndParentFolder(user.getUserId(), parentFolderId);
        return fileInfos;
    }

    @ResponseBody
    @RequestMapping("files/{parentFolderId}")
    public Object getSubFolder(@PathVariable Integer parentFolderId) {
        List<FileInfo> fileInfos = fileInfoService.queryFileByFolder(parentFolderId);
        return fileInfos;
    }

    @ResponseBody
    @RequestMapping("files/null")
    public Object getSubFolder(HttpServletRequest request) {
        String currentUser = CookieUtil.getCookieValue(request, "currentUser");
        User user = userService.queryUserByAccount(currentUser);
        List<FileInfo> fileInfos = fileInfoService.queryRootFileByUserId(user.getUserId());
        return fileInfos;
    }
}
