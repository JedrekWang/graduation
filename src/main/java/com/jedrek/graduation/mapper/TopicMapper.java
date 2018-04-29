package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {

    int addTopic(Topic topic);

    Topic queryTopicById(Integer topicId);

    List<Topic> queryTopicByAccount(String topicStartAccount);

    Topic queryTopicByFileInfo(Integer fileInfoId);

    int updateTopicStatus(Integer topicId);

    int selectLastInsert();

}
