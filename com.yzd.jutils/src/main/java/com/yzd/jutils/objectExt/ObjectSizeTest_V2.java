package com.yzd.jutils.objectExt;

import com.yzd.jutils.person.Person;
import org.apache.lucene.util.RamUsageEstimator;
import org.junit.Test;

public class ObjectSizeTest_V2 {
    /**
     * //计算指定对象及其引用树上的所有对象的综合大小，单位字节
     * long RamUsageEstimator.sizeOf(Object obj)
     *
     * //计算指定对象本身在堆空间的大小，单位字节
     * long RamUsageEstimator.shallowSizeOf(Object obj)
     *
     * //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
     *
     * String RamUsageEstimator.humanSizeOf(Object obj)
     * ————————————————
     * 点评：使用该第三方工具比较简单直接，主要依靠JVM本身环境、参数及CPU架构计算头信息，再依据数据类型的标准计算实例字段大小，计算速度很快，另外使用较方便。
     * 如果非要说这种方式有什么缺点的话，那就是这种方式计算所得的对象头大小是基于JVM声明规范的，并不是通过运行时内存地址计算而得，存在与实际大小不符的这种可能性。
     * ————————————————
     * 对象内存计算神器
     * 原文链接：https://blog.csdn.net/linzhiqiang0316/java/article/details/94214255
     */
    @Test
    public void test(){
        //目前暂时推使用“ObjectSizeTest_V2”版本
        System.out.println(RamUsageEstimator.sizeOf(new Person()));
        System.out.println(RamUsageEstimator.sizeOf(new Person(1,"1","2",3)));
        System.out.println("humanSizeOf:"+RamUsageEstimator.humanSizeOf(new Person(1,"1","2",3)));
        System.out.println("shallowSizeOf:"+RamUsageEstimator.shallowSizeOf(new Person(1,"1","2",3)));
        System.out.println("sizeOf:"+RamUsageEstimator.sizeOf(new Person(1,"1","2",3)));
        //字节单位换算
        System.out.println(RamUsageEstimator.humanReadableUnits(10000));
        //50 MB
        System.out.println(RamUsageEstimator.ONE_MB*50);
        System.out.println(RamUsageEstimator.humanReadableUnits(RamUsageEstimator.ONE_MB*50));

    }
    @Test
    public void allFieldSizeTest(){
        Person person = new Person(1, "1", "2", 3);
        System.err.println(FieldSizeUtil.getString(person,Person.class));
    }
}
