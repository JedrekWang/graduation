1. thymeleaf 和html5部分解析格式不一致，添加
spring.thymeleaf.mode=LEGACYHTML5
使thymeleaf兼容html5

2. 默认的静态资源解析路径为 resources/static ，调用时直接用路径即可表示static下面的文件
3. css中调用的图片路径使用相对路径