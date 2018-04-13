package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.FileInfo;
import com.jedrek.graduation.mapper.FileInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileInfoService {

    private FileInfoMapper fileInfoMapper;

    @Autowired
    public FileInfoService(FileInfoMapper fileInfoMapper) {
        this.fileInfoMapper = fileInfoMapper;
    }

    public int addFile(FileInfo fileInfo) {
        return fileInfoMapper.addFile(fileInfo);
    }

    public int deleteFile(Integer fileId) {
        return fileInfoMapper.deleteFile(fileId);
    }

    public List<FileInfo> queryRootFileByUserId(Integer createdUserId) {
        return fileInfoMapper.queryRootFileByUserId(createdUserId);
    }

    public List<FileInfo> queryFileByFolder(Integer parentFolderId) {
        return fileInfoMapper.queryFileByFolder(parentFolderId);
    }

    public int updateFileInfoName(Integer fileId, String newFileName) {
        return fileInfoMapper.updateFileInfoName(fileId, newFileName);
    }
}
