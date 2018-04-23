package com.jedrek.graduation.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private  Integer messageId;
    private String sendAccount;
    private String receiveAccount;
    private String content;
    private Date sendDate;
    private Integer topic_id;
}
