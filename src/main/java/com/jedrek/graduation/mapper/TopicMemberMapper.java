package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.TopicMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMemberMapper {

    int addTopicMember(TopicMember topicMember);

    List<TopicMember> queryAllMembers(Integer topicId);

    List<Topic> queryAllTopicByAccount(String account);

    List<Topic> queryNotFinishedTopicByAccount(String account);

    List<Topic> queryFinishedTopicByAccount(String account);

}
