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
    private Date createdUserDate;
    private Integer createdUserId;
    private Integer mode; // 0 小组  1 公共  2 私有 3 临时或版本

}
