package com.jedrek.graduation.entity;

import lombok.Data;

@Data
public class Version {
    private Integer versionId;
    private String versionKey;
    private Integer fileId;
    private Integer rawFileId;
    private String versionDesc;
}
