package com.yzd.jutils.objectExt;

import com.carrotsearch.sizeof.RamUsageEstimator;
import com.yzd.jutils.person.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectSizeTest_V1 {
    /**
     * Java一个对象占用内存的大小
     * https://jingyan.baidu.com/article/cd4c297925f231756e6e600f.html
     */
    @Test
    public void test() {
        String str="parent";
        System.out.println(RamUsageEstimator.sizeOf(str));
        System.out.println(RamUsageEstimator.sizeOf(System.currentTimeMillis()));
        System.out.println(RamUsageEstimator.sizeOf(1));
        System.out.println(RamUsageEstimator.sizeOf(1L));
        System.out.println(RamUsageEstimator.sizeOf(1f));
        System.out.println(RamUsageEstimator.sizeOf(1.0));
        System.out.println(RamUsageEstimator.sizeOf(new int[]{}));
        System.out.println(RamUsageEstimator.sizeOf(new ArrayList()));
        System.out.println(RamUsageEstimator.sizeOf(new HashMap()));
        System.out.println(RamUsageEstimator.sizeOf(new Person()));
        System.out.println(RamUsageEstimator.sizeOf(new Person(1,"1","2",3)));
    }
}
