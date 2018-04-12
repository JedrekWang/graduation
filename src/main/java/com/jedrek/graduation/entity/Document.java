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
    private String documentDesc;
    private String contentUrl;
    private Date createdDate;
    private Integer createdUserId;
    private Date lastModifyDate;
    private Integer lastModifyUserId;
    private Integer rootDocumentId; //文档的最原始编号，用于跟踪所有版本的文档


    // 科研小组信息
    // 在原始文档上新添加一个版本文档，需要那些信息？
}
