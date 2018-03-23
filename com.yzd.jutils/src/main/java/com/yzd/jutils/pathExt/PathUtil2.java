package com.yzd.jutils.pathExt;

import java.io.File;
import java.io.IOException;

/**
 * Java中动态获取项目根目录的绝对路径
 * http://www.360doc.com/content/13/0809/13/1542811_305831037.shtml
 */
public class PathUtil2 {
    private static final String rootPath=getRootPathNotTomcat();
    public static String getRootPath(){
        return rootPath;
    }
    //Java桌面程序中，可以通过(new File("")).getAbsolutePath()获取项目根目录(非Tomcat下)。
    //在Tomcat下运行的类中，(new File("")).getAbsolutePath()获得的路径是Tomcat安装路径下的bin文件夹下，将获得的路径字符串去掉最后的"bin"再添上"webapps\\项目文件夹名"即可。
    //参考：
    // https://www.cnblogs.com/linus-tan/p/8032159.html总结
    //总结：
    //在大多数情况下，我们都是用Java开发Web程序。根据我个人习惯，如果在jsp文件或者Servlet中需要用到项目的根目录，则采用getServletContext().getRealPath("/")，因为这个最方便，不需要进一步处理
    // 如果在Servlet调用的其他普通类中需要获得项目根目录，则使用(new File("")).getAbsolutePath(),再稍作处理。
    // 另外，jsp文件中资源的相对路径在没有特别声明的情况下是相对于本文件所在的位置，可以通过在路径字符串的前端加上".."进入上一级文件夹后继续定位，如"../ckeditor/ckeditor.js"；
    // 默认情况下，指定form的action为某Servlet时，应该使用"/servlet/Servletname"，因为默认生成Servlet时，在web.xml的servlet-mapping项中均是这种形式。
    private static String getRootPathNotTomcat() {
        //系统分割符
        //windows和linux上的兼容性问题的解决办法
        String line= File.separator;
        File t=new File("");

        //输出结果：
        // F:\Github-Me\com.yzd.jutils\com.yzd.jutils [在IDE中执行的输出结果]
        // G:\6 [单独部署的输出结果]
        String path= null;
        try {
            path = t.getCanonicalPath()+line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出结果：
        // file:\F:\Github-Me\com.yzd.jutils\com.yzd.jutils\target\classes\ [在IDE中执行的输出结果]
        //jar:file:\G:\6\com.hb.insure.pay.backend.schedule.consumer-1.0-SNAPSHOT-exec.jar!\ [单独部署的输出结果]
        //String path=Thread.currentThread().getContextClassLoader().getResource("").toString();    //此方法不推荐
        //windows下
        if("\\".equals(line)){//
            path = path.replace("/", "\\");  // 将/换成\\
            return path;
        }
        //linux下
        if("/".equals(line)){
            path = path.replace("\\", "/");
            return path;
        }
        throw new IllegalStateException("");

    }

    /**
     *  转为系统分割符 解决兼容性问题
     */
    public static String convertSeparator(String filePath){
        //系统分割符
        //windows和linux上的兼容性问题的解决办法
        String line= File.separator;
        if("\\".equals(line)){
            filePath = filePath.replace("/", "\\");  // 将/换成\\
            return filePath;
        }
        //linux下
        if("/".equals(line)){
            filePath = filePath.replace("\\", "/");
            return filePath;
        }
        throw new IllegalStateException("");
    }
}
