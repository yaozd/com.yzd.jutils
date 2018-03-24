package com.yzd.jutils.pathExt;

import java.io.File;
import java.io.IOException;

public class PathUtil3 {
    //PathUtil3 是PathUtil2的整理版
    private static final String rootPath=getRootPathNotTomcat();
    public static String getRootPath(){
        return rootPath;
    }
    public static String getDownloadPath(){
        String downloadFolder=getRootPath()+"downloadFile"+ File.separator;
        File tmp=new File(downloadFolder);
        if(!tmp.exists()){
            tmp.mkdir();
        }
        return downloadFolder;
    }
    //Java桌面程序中，可以通过(new File("")).getAbsolutePath()获取项目根目录(非Tomcat下)。
    //在Tomcat下运行的类中，(new File("")).getAbsolutePath()获得的路径是Tomcat安装路径下的bin文件夹下，将获得的路径字符串去掉最后的"bin"再添上"webapps\\项目文件夹名"即可。
    private static String getRootPathNotTomcat() {
        //系统分割符
        //windows和linux上的兼容性问题的解决办法
        File tmp=new File("");
        String path= null;
        try {
            path = tmp.getCanonicalPath()+File.separator;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertSeparator(path);
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
