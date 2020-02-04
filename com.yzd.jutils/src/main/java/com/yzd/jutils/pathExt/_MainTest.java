package com.yzd.jutils.pathExt;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class _MainTest {
    @Test
    public void t1() {
        System.out.println(PathUtil2.getRootPath());
    }

    //java中File类的getPath(),getAbsolutePath(),getCanonicalPath()区别
    //https://www.cnblogs.com/newcaoguo/p/6224071.html
    //getPath():
    //
    //返回的是定义时的路径，可能是相对路径，也可能是绝对路径，这个取决于定义时用的是相对路径还是绝对路径。如果定义时用的是绝对路径，那么使用getPath()返回的结果跟用getAbsolutePath()返回的结果一样
    //
    //getAbsolutePath():
    //
    //返回的是定义时的路径对应的相对路径，但不会处理“.”和“..”的情况
    //
    //getCanonicalPath():
    //
    //返回的是规范化的绝对路径，相当于将getAbsolutePath()中的“.”和“..”解析成对应的正确的路径
    @Test
    public void t2() throws IOException {
        File file = new File(".\\test.txt");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());

    }
}
