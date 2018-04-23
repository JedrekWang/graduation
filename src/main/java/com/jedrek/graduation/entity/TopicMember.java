package com.jedrek.graduation.entity;

import lombok.Data;

@Data
public class TopicMember {
    private Integer topicMemberId;
    private String account;
    private Integer topicId;
}
