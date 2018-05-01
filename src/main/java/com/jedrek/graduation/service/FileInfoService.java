package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.*;
import com.jedrek.graduation.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.GroupSequence;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FileInfoService {

    private FileInfoMapper fileInfoMapper;
    private TopicMapper topicMapper;
    private MessageMapper messageMapper;
    private UserGroupMapper userGroupMapper;
    private UserMapper userMapper;
    private UserGroupConMapper userGroupConMapper;
    @Autowired
    public FileInfoService(
            FileInfoMapper fileInfoMapper,
            TopicMapper topicMapper,
            MessageMapper messageMapper,
            UserGroupMapper userGroupMapper,
            UserMapper userMapper,
            UserGroupConMapper userGroupConMapper) {
        this.fileInfoMapper = fileInfoMapper;
        this.topicMapper = topicMapper;
        this.messageMapper = messageMapper;
        this.userGroupMapper = userGroupMapper;
        this.userMapper = userMapper;
        this.userGroupConMapper = userGroupConMapper;
    }


    public int addFile(FileInfo fileInfo) {
        int i = fileInfoMapper.addFile(fileInfo);
        if(i > 0) {
            return topicMapper.selectLastInsert();
        }
        return -1;
    }

    public int deleteFile(Integer fileId) {
        return fileInfoMapper.deleteFile(fileId);
    }

    public FileInfo queryFileByFileId(Integer fileId) {
        return fileInfoMapper.queryFileByFileId(fileId);
    }

    public List<FileInfo> queryRootFileByUserId(Integer createdUserId, Integer mode) {
        return fileInfoMapper.queryRootFileByUserId(createdUserId, mode);
    }

    public List<FileInfo> queryFilesByUserAndParentFolder(Integer createdUserId, Integer parentFolderId) {
        return fileInfoMapper.queryFilesByUserAndParentFolder(createdUserId, parentFolderId);
    }

    public List<FileInfo> queryFileByFolder(Integer parentFolderId) {
        return fileInfoMapper.queryFileByFolder(parentFolderId);
    }

    public int updateFileInfoName(Integer fileId, String newFileName) {
        return fileInfoMapper.updateFileInfoName(fileId, newFileName);
    }

    public List<Message> queryFinishedFileInfoMessageContent(Integer fileInfoId) {
        Topic topic = topicMapper.queryTopicByFileInfo(fileInfoId);
        List<Message> messages = messageMapper.queryMessageByTopicId(topic.getTopicId());
        return messages;
    }

    public List<FileInfo> queryRootFileInfoByGroupId(Integer groupId) {
        List<User> users = userGroupConMapper.queryUsersByGroup(groupId);
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (User user : users) {
            List<FileInfo> fileInfos = fileInfoMapper.queryRootFileByUserIdAndGroupId(user.getUserId(), 0,groupId);
            fileInfoList.addAll(fileInfos);
        }
        return fileInfoList;
    }

    public List<FileInfo> queryFileInfoByGroupId(Integer groupId, Integer parentFolderId) {
        List<User> users = userGroupConMapper.queryUsersByGroup(groupId);
        List<FileInfo> fileInfoList = new ArrayList<>();
        for(User user : users) {
            List<FileInfo> fileInfos = fileInfoMapper.queryFilesByUserAndParentFolder(user.getUserId(), parentFolderId);
            fileInfoList.addAll(fileInfos);
        }
        return fileInfoList;
    }
}
