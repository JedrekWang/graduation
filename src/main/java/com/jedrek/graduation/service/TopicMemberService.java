package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.TopicMember;
import com.jedrek.graduation.mapper.TopicMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicMemberService {

    private TopicMemberMapper topicMemberMapper;

    @Autowired
    public TopicMemberService(TopicMemberMapper topicMemberMapper) {
        this.topicMemberMapper = topicMemberMapper;
    }

   public int addTopicMember(TopicMember topicMember) {
        return topicMemberMapper.addTopicMember(topicMember);
   }

   public List<TopicMember> queryAllMembers(Integer topicId) {
        return topicMemberMapper.queryAllMembers(topicId);
   }

   public List<Topic> queryAllTopicByAccount(String account) {
        return topicMemberMapper.queryAllTopicByAccount(account);
   }

   public List<Topic> queryNotFinishedTopicByAccount(String account) {
        return topicMemberMapper.queryNotFinishedTopicByAccount(account);
   }

   public List<Topic> queryFinishedTopicByAccount(String account) {
        return topicMemberMapper.queryFinishedTopicByAccount(account);
   }
}
