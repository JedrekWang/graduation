package com.jedrek.graduation.web;

import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.entity.*;
import com.jedrek.graduation.service.FileInfoService;
import com.jedrek.graduation.service.MessageService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.service.VersionService;
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
    private VersionService versionService;
    private MessageService messageService;

    @Autowired
    public FileInfoController(FileInfoService fileInfoService, UserService userService, VersionService versionService, MessageService messageService) {
        this.fileInfoService = fileInfoService;
        this.userService = userService;
        this.versionService = versionService;
        this.messageService = messageService;
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
            @RequestParam Integer mode,
            @RequestBody MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String[] split = filename.split("\\.", 2);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setParentFolderId(parentFolderId);
            fileInfo.setFileName(split[0]);
            fileInfo.setFormat(split[1]);
            fileInfo.setMode(mode);
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
    @RequestMapping(value="file/comment", method=RequestMethod.POST)
    public Object uploadFileInfo(
            HttpServletRequest request,
            @RequestParam String version,
            @RequestBody MultipartFile file,
            @RequestParam String comment,
            @RequestParam Integer topicId,
            @RequestParam Integer fileInfoId) throws Exception {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            String[] split = filename.split("\\.", 2);
            FileInfo fileInfo = new FileInfo();
//            fileInfo.setParentFolderId(null);
            fileInfo.setFileName(split[0]);
            fileInfo.setFormat(split[1]);
            fileInfo.setMode(3);
            String currentUser = CookieUtil.getCookieValue(request, "currentUser");
            if (currentUser != null) {
                User user = userService.queryUserByAccount(currentUser);
                fileInfo.setCreatedUserId(user.getUserId());
                String path = DocumentUtil.handleDocumentPath(filename, new Date());
                fileInfo.setContentUrl(path);
                int fileId = fileInfoService.addFile(fileInfo);
                Version fileVersion = new Version();
                fileVersion.setFileId(fileId);
                fileVersion.setRawFileId(fileInfoId);
                fileVersion.setVersionDesc(comment);
                if(Objects.equals(version, "版本文档")) {
                    fileVersion.setVersionKey("版本" + fileId);
                } else {
                    fileVersion.setVersionKey("普通"+fileId);
                }
                versionService.addVersion(fileVersion);
                Message message = new Message();
                message.setTopicId(topicId);
                message.setSendAccount(currentUser);
                message.setContent(comment);
                message.setMode(1);
                messageService.addMessage(message);

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
                return "success";
            }
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping("{userName}/rootFiles")
    public Object getRootFiles(@PathVariable String userName, @RequestParam Integer mode) {
        User user = userService.queryUserByAccount(userName);
        List<FileInfo> fileInfos = fileInfoService.queryRootFileByUserId(user.getUserId(), mode);
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
        List<FileInfo> fileInfos = fileInfoService.queryRootFileByUserId(user.getUserId(), 0);
        return fileInfos;
    }

    @ResponseBody
    @RequestMapping("deleteFile/{fileId}")
    public Object deleteFile(@PathVariable Integer fileId) {
        int i = fileInfoService.deleteFile(fileId);
        if (i > 0) {
            return "success";
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping("file/comment/{fileId}")
    public Object getFileInfoFinishedComment(@PathVariable Integer fileId) {
        List<Message> messages = fileInfoService.queryFinishedFileInfoMessageContent(fileId);
        return messages;
    }
}
