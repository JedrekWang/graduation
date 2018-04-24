package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private  Integer messageId;
    private String sendAccount;
    private String content;
    private Date sendDate;
    private Integer topicId;
}
