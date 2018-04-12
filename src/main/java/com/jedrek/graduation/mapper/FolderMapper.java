package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FolderMapper {

    int addFolder(Folder folder);

    int deleteFolder(Integer folderId);

    List<Folder> queryRootFolderByUser(Integer createdUserId);

    int updateFolderName(
            @Param("folderId") Integer folderId,
            @Param("newName") String newName);
}
