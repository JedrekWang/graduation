package com.jedrek.graduation.constant;

import org.springframework.util.ClassUtils;

import javax.validation.constraints.NotNull;
import java.nio.file.Path;

public class Constant {
    @NotNull
    public static final String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();

    public static final String uploadPath = "D:/documentSystem/";

    public static final String headerPath = "D:/documentSystem/img/";

    public static final String documentPath = "/system/";


}
