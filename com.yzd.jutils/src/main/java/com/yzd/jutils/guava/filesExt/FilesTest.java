package com.yzd.jutils.guava.filesExt;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by zd.yao on 2018/9/8.
 */
public class FilesTest {
    //关于Guava中I/O中Files类各个方法的解读
    //https://blog.csdn.net/Husc2009/article/details/7797577
    //Files类的简单使用-代码优雅之道Guava(二)
    //https://blog.csdn.net/chenleixing/article/details/44994815
    /**
     * Files读写测试
     * @author chenleixing
     */
    @Test
    public void testFiles() throws IOException {
        //复制移动文件
        File from=new File("D:\\chen.txt");
        File to=new File("D:\\wo.txt");
        Files.copy(from, to);
        //Files.move(from, to);//会删除掉原来的文件
        String text = Files.toString(from,Charsets.UTF_8);
        System.out.println(text);
        //一行代码读取内容存入list中
        List<String> list=Files.readLines(from, Charsets.UTF_8);

        //一行代码对文件进行内容的追加 1
        Files.append("zhuijianeirong",to,Charsets.UTF_8);
        //这里如果c.txt文件存在，但是a文件夹不存在，就会被创建。
        File file = new File("a/c.txt");
        Files.createParentDirs(file);

    }

}
