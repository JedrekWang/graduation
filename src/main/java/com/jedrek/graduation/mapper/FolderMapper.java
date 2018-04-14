package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface FolderMapper {

    int addFolder(Folder folder);

    int deleteFolder(Integer folderId);

    Folder queryFolderById(Integer folderId);

    List<Folder> queryRootFolderByUser(Integer createdUserId);

    List<Folder> querySubFolder(Integer parentFolderId);

    Folder queryFolder(
            @Param("parentFolderId") Integer parentFolderId,
            @Param("folderName") String folderName);

    int updateFolderName(
            @Param("folderId") Integer folderId,
            @Param("newName") String newName);
}
