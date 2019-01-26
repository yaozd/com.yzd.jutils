package com.yzd.jutils.elkExt;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 生成1亿数据，用于ES框架搭建使用
 * 日志生成在当前目录下
 */
public class GenerateBigLog {

    private static final String ENCODING = "UTF-8";
    private static final int NUM = 50000;
    //当前目录下
    private static File file = new File("test.txt");

    /**
     * 生成1000w随机文本文件
     */
    public static void makePin() {
        System.out.println("生成1亿条测试数据-正在执行……");
        String prefix = "_$#";
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file, true), ENCODING);
            // 在1500w里随机1000w数据
            for (int j = 0; j < 100000000; j++) {
                out.write(prefix + (int) (130000000 * Math.random()) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
        System.out.println("生成1亿条测试数据-执行完成");
        System.out.println("数据文件路径：");
        System.out.println(file.getAbsolutePath());
    }
}
