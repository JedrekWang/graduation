1. thymeleaf 和html5部分解析格式不一致，添加
spring.thymeleaf.mode=LEGACYHTML5
使thymeleaf兼容html5

2. 默认的静态资源解析路径为 resources/static ，调用时直接用路径即可表示static下面的文件
3. css中调用的图片路径使用相对路径
4. pom.xml使用spring boot必须使用spring提供的mybatis-spring-boot-starter包而不是单独的mybatis包，负责spring boot无法
找到mybatis自己生成的dao层代码
5. 在mybatis的setting中配置了转驼峰式但取出的对象不支持？原因不明，解决办法
在写sql时将对象的字段作为别名
6. spring boot默认的访问静态文件和请求映射的动态url冲突导致静态资源无法访问，
解决办法为通过配置类来重新映射
7. 实体类的主键没写，实体类的配置文件标签写错了insert写为了select,忘记加@Mapper注解
8. Vue实例的标签范围不能重叠
9. JS的this指针隐性变换
10. axios的异步方法使得执行的顺序无法保证,如果需要顺序保证通过多个then(),需要一起执行用axios.all
11. 在Vue.js页面里面使用普通的同步提交表单