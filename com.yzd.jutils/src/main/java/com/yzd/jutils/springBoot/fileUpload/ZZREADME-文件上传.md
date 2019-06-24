### 上传文件设置绝对路径和访问绝对路径下的静态资源
- [https://blog.csdn.net/superlover_/article/details/80893007](https://blog.csdn.net/superlover_/article/details/80893007)

### 参考：
- com.jebao.file.web
- com.yzhs.cms.root=》CicadasCms
```
#文件上传目录（注意Linux和Windows上的目录结构不同）
#file.uploadFolder=/root/uploadFiles/
file.uploadFolder=/data/uploadFiles
#--------------------------------------#
--------------
@Configuration
public class FileUploadConfig {
    //限制上传文件的大小
    //默认情况下是1MB的大小
    //上传文件的小最好设置为2M
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20MB");
        //factory.setMaxRequestSize("128KB");
        //factory.setMaxFileSize(1024L * 1024L* 1024L);
        if(Platform.isLinux())
            factory.setLocation("/var/tmp");
        return factory.createMultipartConfig();
    }

}
--------------
    @Value("${file.uploadFolder}")
    private String rootPath4UploadFolder;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + rootPath4UploadFolder+ File.separator);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //在生产环境中禁打开/swagger-ui.html
        if (!swaggerShow) {
            registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/noswagger");
        }
    }
--------------

```