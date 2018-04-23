package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Topic {
    private Integer topicId;
    private String topicStartAccount;
    private Integer fileInfoId;
    private Date topicStartDate;
    private Integer finish;

}
