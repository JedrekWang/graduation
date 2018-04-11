package com.jedrek.graduation.service;

import com.jedrek.graduation.entity.Version;
import com.jedrek.graduation.mapper.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VersionService {
    private VersionMapper versionMapper;

    @Autowired
    public VersionService(VersionMapper versionMapper) {
        this.versionMapper = versionMapper;
    }

    public List<Version> queryVersionsByDocumentId(Integer documentId) {
        return versionMapper.queryVersionsByDocumentId(documentId);
    }

    public Version queryVersionByKey(String versionKey){
        return versionMapper.queryVersionByKey(versionKey);
    }

    public int addVersion(Version version) {
        return versionMapper.addVersion(version);
    }

    public int deleteVersion(String versionKey) {
        return versionMapper.deleteVersion(versionKey);
    }
}
