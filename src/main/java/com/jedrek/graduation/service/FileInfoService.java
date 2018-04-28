package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.FileInfo;
import com.jedrek.graduation.mapper.FileInfoMapper;
import com.jedrek.graduation.mapper.TopicMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileInfoService {

    private FileInfoMapper fileInfoMapper;
    private TopicMapper topicMapper;

    @Autowired
    public FileInfoService(FileInfoMapper fileInfoMapper, TopicMapper topicMapper) {
        this.fileInfoMapper = fileInfoMapper;
        this.topicMapper = topicMapper;
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
}
