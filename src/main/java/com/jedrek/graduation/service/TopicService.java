package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {

    private TopicMapper topicMapper;

    @Autowired
    public TopicService(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
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

   public int updateTopicStatus(Integer topicId) {
       return topicMapper.updateTopicStatus(topicId);
   }
}
