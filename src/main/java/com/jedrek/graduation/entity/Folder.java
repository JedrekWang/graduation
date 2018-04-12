package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 *  文件夹
 */

@Data
public class Folder {
    private Integer folderId;
    private String folderName;
    private String folderDesc;
    private Integer parentFolderId;
    private String contentUrl;
    private Integer createdUserId;
    private Date createdUserDate;
    private Set<FileInfo> fileInfos;
}
