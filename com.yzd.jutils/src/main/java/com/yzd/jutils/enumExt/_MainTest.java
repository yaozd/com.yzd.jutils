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

    // 通过hashMap提高执行效率
    // 主要场景是在频繁转换时使用
    @Test
    public void t2(){
        String n1= NameEnum.getName(10);
        PrintUtil.outLn(n1);
        String n2= NameEnum.getName(1);
        PrintUtil.outLn(n2);
    }
    //
    //NameEnum2 与NameEnum的不同，在于
    //nameMap= EnumHelper.getNameMap(NameEnum2.class,"getName","getValue");
    //转为
    //nameMap=getNameMap();
    @Test
    public void t3(){
        String n1= NameEnum2.getName(10);
        PrintUtil.outLn(n1);
        String n2= NameEnum2.getName(1);
        PrintUtil.outLn(n2);
    }
}
