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

    FileInfo queryFileByFileId(Integer fileId);

    List<FileInfo> queryRootFileByUserId(
            @Param("createdUserId") Integer createdUserId,
            @Param("mode") Integer mode);

    List<FileInfo> queryFilesByUserAndParentFolder(
            @Param("createdUserId") Integer createdUserId,
            @Param("parentFolderId") Integer parentFolderId);

    List<FileInfo> queryFileByFolder(Integer parentFolderId);

    int updateFileInfoName(
            @Param("fileId") Integer fileId,
            @Param("newFileName") String newFileName);

}
