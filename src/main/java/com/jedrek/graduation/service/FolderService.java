package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.FileInfo;
import com.jedrek.graduation.entity.Folder;
import com.jedrek.graduation.entity.User;
import com.jedrek.graduation.mapper.FolderMapper;
import com.jedrek.graduation.mapper.UserGroupConMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FolderService {

    private FolderMapper folderMapper;
    private UserGroupConMapper userGroupConMapper;


    @Autowired
    public FolderService(FolderMapper folderMapper, UserGroupConMapper userGroupConMapper) {
        this.folderMapper = folderMapper;
        this.userGroupConMapper = userGroupConMapper;
    }

    public int addFolder(Folder folder) {
        return folderMapper.addFolder(folder);
    }

    public int deleteFolder(Integer folderId) {
        return folderMapper.deleteFolder(folderId);
    }

    public Folder queryFolderById(Integer folderId) {
        return folderMapper.queryFolderById(folderId);
    }

    public List<Folder> queryRootFolderByUser(Integer createdUserId, Integer mode) {
        return folderMapper.queryRootFolderByUser(createdUserId, mode);
    }

    public List<Folder> querySubFolder(Integer parentFolderId) {
        return folderMapper.querySubFolder(parentFolderId);
    }

    public Folder queryFolder(
            @Param("parentFolderId") Integer parentFolderId,
            @Param("folderName") String folderName) {
        return folderMapper.queryFolder(parentFolderId, folderName);
    }

    public int updateFolderName(Integer folderId, String newName) {
        return folderMapper.updateFolderName(folderId, newName);
    }

    public List<Folder> queryRootFolderByGroupId(Integer groupId) {
        List<User> users = userGroupConMapper.queryUsersByGroup(groupId);
        List<Folder> folderList = new ArrayList<>();
        for (User user : users) {
            List<Folder> folders = folderMapper.queryRootFolderByUserAndGroupId(user.getUserId(), 0, groupId);
            folderList.addAll(folders);
        }
        return folderList;
    }

    public List<Folder> queryFolderByGroupId(Integer groupId, Integer parentFolderId) {
        List<User> users = userGroupConMapper.queryUsersByGroup(groupId);
        List<Folder> folderList = new ArrayList<>();
        for(User user : users) {
            List<Folder> folders = folderMapper.querySubFolder(parentFolderId);
            folderList.addAll(folders);
        }
        return folderList;
    }

}
