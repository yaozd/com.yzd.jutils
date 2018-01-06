package com.yzd.jutils.enumExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

/**
 * Created by zd.yao on 2018/1/6.
 */
public class _MainTest {

    //java enum(枚举)使用详解 + 总结
    //https://www.cnblogs.com/hyl8218/p/5088287.html
    //====================
    //通过枚举作为：以注解形式的缓存key的一部分可以知道，用那些地方使用了此枚举
    //枚举的name属性可以确定KEY的唯一性。
    @Test
    public void t1(){
        String n1=EnumTest.FRI.name();
        //print:name=FRI
        PrintUtil.outLn("name="+n1);
    }
}
