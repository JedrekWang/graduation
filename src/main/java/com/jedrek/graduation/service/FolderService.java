package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Folder;
import com.jedrek.graduation.mapper.FolderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FolderService {

    private FolderMapper folderMapper;

    @Autowired
    public FolderService(FolderMapper folderMapper) {
        this.folderMapper = folderMapper;
    }

    public int addFolder(Folder folder) {
        return folderMapper.addFolder(folder);
    }

    public int deleteFolder(Integer folderId) {
        return folderMapper.deleteFolder(folderId);
    }

    public List<Folder> queryRootFolderByUser(Integer createdUserId) {
        return folderMapper.queryRootFolderByUser(createdUserId);
    }

    public List<Folder> querySubFolder(Integer parentFolderId) {
        return folderMapper.querySubFolder(parentFolderId);
    }

    public int updateFolderName(Integer folderId, String newName) {
        return folderMapper.updateFolderName(folderId, newName);
    }
}
