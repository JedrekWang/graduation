package com.jedrek.graduation.entity;


import lombok.Data;

import java.util.Date;

/**
 * 文档实体类
 */
@Data
public class Document {
    private Integer documentId;
    private String title;
    private String contentUrl;
    private Date createdDate;
    private Integer createdUserId;
    private Date lastModifyDate;
    private Integer lastModifyUserId;
}
