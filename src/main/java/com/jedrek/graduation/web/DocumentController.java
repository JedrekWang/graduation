package com.jedrek.graduation.web;

import com.alibaba.fastjson.JSON;
import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.entity.Document;
import com.jedrek.graduation.service.DocumentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * 用户文档信息展示
     * @param userName
     * @param documentName
     * @return
     */
    @RequestMapping(value = "{userName}/{documentName}" , method = RequestMethod.GET)
    public String showDocumentInfo(@PathVariable String userName, @PathVariable String documentName) {
        return null;
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
