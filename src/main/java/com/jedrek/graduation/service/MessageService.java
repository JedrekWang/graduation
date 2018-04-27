package com.jedrek.graduation.service;

import com.jedrek.graduation.constant.Constant;
import com.jedrek.graduation.entity.FileInfo;
import com.jedrek.graduation.entity.Message;
import com.jedrek.graduation.entity.Topic;
import com.jedrek.graduation.entity.Version;
import com.jedrek.graduation.mapper.FileInfoMapper;
import com.jedrek.graduation.mapper.MessageMapper;
import com.jedrek.graduation.mapper.TopicMapper;
import com.jedrek.graduation.mapper.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class MessageService {
    private MessageMapper messageMapper;
    private TopicMapper topicMapper;
    private VersionMapper versionMapper;
    private FileInfoMapper fileInfoMapper;

    @Autowired
    public MessageService(
            MessageMapper messageMapper,
            TopicMapper topicMapper,
            VersionMapper versionMapper,
            FileInfoMapper fileInfoMapper) {
        this.messageMapper = messageMapper;
        this.topicMapper = topicMapper;
        this.versionMapper = versionMapper;
        this.fileInfoMapper = fileInfoMapper;
    }

    public int addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

    //列出讨论内容
    public List<Message> queryMessageByTopicId(Integer topicId) {
        return messageMapper.queryMessageByTopicId(topicId);
    }

    public String getMessageUploadFilePath(Integer messageId) {
        Message message = messageMapper.queryMessageById(messageId);
        if(message.getMode() == 1) {
            Integer topicId = message.getTopicId();
            Topic topic = topicMapper.queryTopicById(topicId);
            List<Version> versionList = versionMapper.queryVersionsByRawFileId(topic.getFileInfoId());
            for(Version version: versionList) {
                if (Objects.equals(version.getVersionDesc(), message.getContent())) {
                    FileInfo fileInfo = fileInfoMapper.queryFileByFileId(version.getFileId());
                    return Constant.documentPath+fileInfo.getContentUrl();
                }
            }
        }
        return null;
    }
}
