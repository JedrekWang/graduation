package com.jedrek.graduation.constant;

import org.springframework.util.ClassUtils;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;

public class Constant {
    @NotNull
    public static final String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static final String uploadPath = rootPath + "/static/upload/";

    public static final String documentPath = "D:/documentSystem/";


}
