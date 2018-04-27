package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.TopicMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMemberMapper {

    int addTopicMember(TopicMember topicMember);

    List<TopicMember> queryAllMembers(Integer topicId);

    List<TopicMember> queryAllTopicByAccount(String account);

    List<TopicMember> queryNotFinishedTopicByAccount(String account);

    List<TopicMember> queryFinishedTopicByAccount(String account);

}
