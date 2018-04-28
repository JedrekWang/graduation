package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Message;
import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.TopicMember;
import com.jedrek.graduation.mapper.MessageMapper;
import com.jedrek.graduation.mapper.TopicMapper;
import com.jedrek.graduation.mapper.TopicMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {

    private TopicMapper topicMapper;
    private TopicMemberMapper topicMemberMapper;
    private MessageMapper messageMapper;

    @Autowired
    public TopicService(TopicMapper topicMapper, TopicMemberMapper topicMemberMapper, MessageMapper messageMapper) {
        this.topicMapper = topicMapper;
        this.topicMemberMapper = topicMemberMapper;
        this.messageMapper = messageMapper;
    }


    public int addTopic(Topic topic) {
        int i = topicMapper.addTopic(topic);
        if(i > 0) {
            return topicMapper.selectLastInsert();
        }
        return -1;
    }

   public Topic queryTopicById(Integer topicId) {
        return topicMapper.queryTopicById(topicId);
   }

   public List<Topic> queryTopicByAccount(String topicStartAccount) {
       return topicMapper.queryTopicByAccount(topicStartAccount);
   }

   public int closeTopic(Integer topicId) {
       return topicMapper.updateTopicStatus(topicId);
   }

   public List<Message> getTopicAllContent(Integer topicId) {
       List<Message> messages = messageMapper.queryMessageByTopicId(topicId);
       return messages;
   }
}
