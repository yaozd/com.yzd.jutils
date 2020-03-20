###　Spring Boot应用上传文件时报错-multipart.location
- [Spring Boot应用上传文件时报错](https://www.cnblogs.com/nuccch/p/11546494.html)-推荐参考byArvin-2020-03-20
```
spring.servlet.multipart.max-file-size=3072MB
spring.servlet.multipart.max-request-size=3072MB
spring.servlet.multipart.location=/var/tmp
```

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
    //配置静态文件下载
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
@Configuration
public class FileDownloadConfig extends WebMvcConfigurationSupport {
    @Value("${xdja.upload.file.path}")
    private String decryptFilePath;
    
    //配置静态文件下载
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File(decryptFilePath);
        String fullFilePath="file:" + file.getAbsolutePath()+ File.separator;
        registry.addResourceHandler("/file/**").addResourceLocations(fullFilePath);
        //registry.addResourceHandler("/file/**").addResourceLocations("file:" + file+ File.separator);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}

```
## [Spring boot 下载文件 优雅实现](https://blog.csdn.net/q1009020096/article/details/89952141)
```
@GetMapping("/downloadCacheFile/{fileName:.*}")
public ResponseEntity<Resource> downloadCacheFile(@PathVariable("fileName") String fileName) {
    try {
        String savePath = "/home/download/"
        // 获取文件名称，中文可能被URL编码
        fileName = URLDecoder.decode(fileName, "UTF-8");
        // 获取本地文件系统中的文件资源
        FileSystemResource resource = new FileSystemResource(savePath + fileName);

        // 解析文件的 mime 类型
        String mediaTypeStr = URLConnection.getFileNameMap().getContentTypeFor(fileName);
        // 无法判断MIME类型时，作为流类型
        mediaTypeStr = (mediaTypeStr == null) ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mediaTypeStr;
        // 实例化MIME
        MediaType mediaType = MediaType.parseMediaType(mediaTypeStr);


        /*
         * 构造响应的头
         */
        HttpHeaders headers = new HttpHeaders();
        // 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
        String filenames = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        headers.setContentDispositionFormData("attachment", filenames);
        headers.setContentType(mediaType);

        /*
         * 返还资源
         */
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.getInputStream().available())
                .body(resource);
    } catch (IOException e) {
        logger.error("文件读写错误", e);
        return null;
    }
}
————————————————
https://blog.csdn.net/q1009020096/article/details/89952141
```