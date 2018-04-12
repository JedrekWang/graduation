package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文件
 */

@Data
public class FileInfo {
    private Integer fileId;
    private String fileName;
    private String format;
    private Integer parentFolderId;
    private String contentUrl;
    private Date createdDate;
    private Integer createdUserId;

}
