package com.yzd.jutils.print;

/**
 * Created by zd.yao on 2017/4/27.
 */
public class PrintUtil {
    public static void outLn(Object obj) {
        System.out.println(obj);
    }

    public static void outLn(String format, Object... obj) {
        System.out.println(String.format(format, obj));
    }
}

