package com.yzd.jutils.commonsLangExt;

import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestEnumDemo {
    public enum EnumDemo {
        AA("1"), BB("2");
        private String value;

        EnumDemo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /***
     * 枚举工具类
     *
     * getEnum(Class<E> enumClass, String enumName) 通过类返回一个枚举，可能返回空
     *
     * getEnumList(Class<E> enumClass) 通过类返回一个枚举集合
     *
     * getEnumMap(Class<E> enumClass) 通过类返回一个枚举map
     *
     * isValidEnum(Class<E> enumClass, String enumName) 验证enumName是否在枚举中，返回true false
     * ---------------------
     * 作者：allsmallpig
     * 来源：CSDN
     * 原文：https://blog.csdn.net/u012240455/article/details/79014192
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    @Test
    public void t1(){
        EnumDemo enumDemo = EnumUtils.getEnum(EnumDemo.class, "");
        System.out.println(enumDemo);
        System.out.println("-----");

        List<EnumDemo> list = EnumUtils.getEnumList(EnumDemo.class);
        for (EnumDemo a : list) {
            System.out.println(a + ":" + a.getValue());
        }
        System.out.println("-----");

        Map<String, EnumDemo> enumMap = EnumUtils.getEnumMap(EnumDemo.class);
    }
}
