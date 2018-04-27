package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    int addMessage(Message message);

    //列出讨论内容
    List<Message> queryMessageByTopicId(Integer topicId);

    Message queryMessageById(Integer messageId);


}
