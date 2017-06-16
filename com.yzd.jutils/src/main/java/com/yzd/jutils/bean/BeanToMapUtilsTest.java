package com.yzd.jutils.bean;

import com.yzd.jutils.print.PrintUtil;

import java.util.Map;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class BeanToMapUtilsTest {
    /**
     * JavaBean与Map相互转换
     * https://my.oschina.net/sodeve/blog/533933
     * @param args
     */
    public static void main(String[] args) {
        Person person=new Person();
        person.setAge("1");
        person.setName("name");
        Map<String,Object> map= BeanToMapUtils.convertBean(person);
        PrintUtil.outLn(map);
    }
}
