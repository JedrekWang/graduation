package com.jedrek.graduation.constant;

import org.springframework.util.ClassUtils;

import javax.validation.constraints.NotNull;

public class Constant {
    @NotNull
    public static final String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static final String uploadPath = rootPath + "/static/upload/";


}
