package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Message;
import com.jedrek.graduation.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {
    private MessageMapper messageMapper;

    @Autowired
    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    //列出讨论内容
    public List<Message> queryMessageByTopicId(Integer topicId) {
        return messageMapper.queryMessageByTopicId(topicId);
    }
}
