package com.jedrek.graduation.mapper;

import com.jedrek.graduation.entity.Version;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VersionMapper {

    List<Version> queryVersionsByDocumentId(Integer documentId);

    Version queryVersionByKey(String versionKey);

    int addVersion(Version version);

    int deleteVersion(String versionKey);

}
