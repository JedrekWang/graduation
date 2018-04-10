package com.jedrek.graduation.web;

import com.alibaba.fastjson.JSON;
import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.entity.Document;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.service.DocumentService;
import com.jedrek.graduation.service.UserService;
import com.jedrek.graduation.utils.CookieUtil;
import com.jedrek.graduation.utils.DocumentUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DocumentController {

    private DocumentService documentService;
    private UserService userService;

    @Autowired
    public DocumentController(DocumentService documentService, UserService userService) {
        this.documentService = documentService;
        this.userService = userService;
    }

    @RequestMapping("document/test")
    public String showCreateDocument() {
        return "create_document";
    }

    @RequestMapping(value = "document/test", method = RequestMethod.POST)
    public String uploadCreateDocument(
            HttpServletRequest request,
            @RequestParam String title,
            @RequestParam String documentDesc,
            @RequestParam MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            Document document = new Document();
            document.setTitle(title);
            document.setDocumentDesc(documentDesc);
            String currentUser = CookieUtil.getCookieValue(request, "currentUser");
            if (currentUser != null) {
                User user = userService.queryUserByAccount(currentUser);
                document.setCreatedUserId(user.getUserId());
                document.setCreatedDate(new Date());
                String path = DocumentUtil.handleDocumentPath(filename, document.getCreatedDate());
                document.setContentUrl(path);
                documentService.addDocument(document);
                String uploadPath = Constant.uploadPath + path;
                File uploadFile = new File(uploadPath);
                File parentFile = uploadFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                uploadFile.createNewFile();
                file.transferTo(uploadFile);
                // todo 如果为word格式需要在相同路径下面转为pdf
                if (Objects.equals(filename.split("\\.")[1], "docx")) {
                    DocumentUtil.wordToPdf(uploadFile);
                }
                return "redirect:/";
            }

        }
        return "null";
    }

    @RequestMapping("upload_document")
    public String uploadDocument(MultipartFile file) {
        System.out.println("test");
        return "null";
    }

        /**
         * 用户文档信息展示
         * @param userName
         * @param documentId
         * @return
         */
    @RequestMapping(value = "/{userName}/{documentId}" , method = RequestMethod.GET)
    public String showDocumentInfo(@PathVariable String userName, @PathVariable Integer documentId) {
        return "document";
    }



    @ResponseBody
    @RequestMapping("document/{documentId}")
    public Map getDocument(
            @PathVariable Integer documentId) {
        Document document = documentService.queryDocument(documentId);
        Date createdDate = document.getCreatedDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日发布");
        String dateString = formatter.format(createdDate);
        Integer userId = document.getCreatedUserId();
        User user = userService.queryUserById(userId);
        String contentUrl = document.getContentUrl();
        String finalContentUrl = contentUrl.split("\\.", 2)[0];
        String finalPath = Constant.documentPath + finalContentUrl +".pdf";
        Map ans = new HashMap();
        ans.put("user", user);
        ans.put("document", document);
        ans.put("finalPath", finalPath);
        ans.put("dateString", dateString);
        return ans;
    }

    @ResponseBody
    @RequestMapping("{userName}/documents")
    public List<Document> getDocumentsByUser(@PathVariable String userName) {
        //todo 首页只展示最近的三列数据
        //todo 增加添加文档的页面，修改用户信息的页面，展示用户所有的文档，展示用户所在组所有的文档，展示查看组所有成员的页面
        // todo 用户的头像上传功能
        // todo 对文档版本的管理，查看所有版本的页面
        // todo 组员讨论的管理，显示讨论的内容
        // todo 文档的搜索功能
        // todo 首页我的工作台为显示自己创建的所有文档和自己参与修改的文档
        // todo 首页消息中心和别人在自己创建文档上的相关讨论信息，如保留所有讨论的内容
        // todo 小组管理，管理员身份显示，可以增加组员，删除组员，更改权限等操作
        // todo 首页的铃铛显示别人发给自己的所有消息(用于交流)
        // todo 首页的加号为新建文档，貌似功能重复了？？？
        // todo 首页的星星按钮，显示自己收藏的所有文档，功能未要求，不是优选项
        //todo 文档的查看讨论为显示当前文档的讨论内容，每一个组员都可以添加内容
        //todo 查看历史版本可以看到所有的版本，通过点击对应的版本,显示的pdf文档页对应改变
        List<Document> documents = documentService.queryDocumentByUserName(userName);
        return documents;
    }

    @RequestMapping(value = "{userName}/{documentName}", method = RequestMethod.POST)
    public String updateDocumentInfo(@PathVariable String userName, @PathVariable String documentName) {
        return null;
    }

    /**
     * 展示文档预览的页面，即弹出pdf文档的页面
     * @param userName
     * @param documentName
     * @return
     */
    @RequestMapping(value = "{userName}/{documentName}/preview", method = RequestMethod.GET)
    public String showPreview(@PathVariable String userName, @PathVariable String documentName) {
        return null;
    }

    /**
     * 展示文档的历史修改记录
     * @param userName
     * @param documentName
     * @return
     */
    @RequestMapping(value = "{userName}/{documentName}/log", method = RequestMethod.GET)
    public String showDocumentLog(@PathVariable String userName, @PathVariable String documentName) {
        return null;
    }

    /**
     * 展示文档的相关话题讨论
     * @param userName
     * @param documentName
     * @return
     */
    @RequestMapping(value = "{userName}/{documentName}/discuss", method = RequestMethod.GET)
    public String showDocumentDiscuss(@PathVariable String userName, @PathVariable String documentName) {
        return null;
    }

    @RequestMapping(value = "{userName}/{documentName}/archive/{fileName}", method = RequestMethod.GET)
    public String downloadDocument(@PathVariable String userName,
                                   @PathVariable String documentName,
                                   @PathVariable String fileName) {
        return null;
    }

    /**
     * 上传文件
     * @return
     * @deprecated
     */
    @RequestMapping(value = "document/upload", method = RequestMethod.GET)
    public String showUploadDocument() {
        return "upload";
    }

    /**
     * 上传文件
     * @param file
     * @return
     * @deprecated
     */
    @ResponseBody
    @RequestMapping(value = "document/upload", method = RequestMethod.POST)
    public String uploadDocument123(MultipartFile file) {
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

    /**
     * 下载文件
     * @param filename
     * @return
     * @throws Exception
     * @deprecated
     */
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

    /**
     * @deprecated
     * @param request
     * @return
     */
    @RequestMapping(value = "documents/1", method = RequestMethod.GET)
    public String showDocument(HttpServletRequest request) {
        // TODO  将转换好的pdf文件放在resources/static下面的目录可以直接通过url访问该资源
        // TODO 因此我们需要做的只是根据id找到上传文档的全路径，转换为pdf放在static目录下，
        // TODO 然后返回一个新的访问静态资源的url
        String dir = "C:\\Users\\wangjie22438\\Desktop\\testdoc.pdf";
        String path = request.getRequestURL() + dir.substring(dir.lastIndexOf('\\'));
        String retString = path.replaceAll("\\\\","/");
        Map<String,Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("pdf", retString);
        return JSON.toJSONString(map);
//
//        String retString = null;
//        String dir = XXXX文件在服务器中路径。
//        String path = httpServletRequest.getRequestURL() + dir.substring(dir.lastIndexOf('\\'));
//        retString = path.replaceAll("\\\\","/");
//        Map<String,Object >map = new HashMap<>();
//        map.put("code",0);
//        map.put("pdf",retString);
//        return JSON.toJSONString(map);
    }
}
