package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.File;
import java.util.List;


@Mapper
public interface FileInfoMapper {

    int addFile(FileInfo fileInfo);

    int deleteFile(Integer fileId);

    List<FileInfo> queryFileByUserId(Integer createdUserId);

    List<FileInfo> queryFileByFolder(Integer parentFolderId);

    int updateFileInfoName(
            @Param("fileId") Integer fileId,
            @Param("newFileName") String newFileName);

}