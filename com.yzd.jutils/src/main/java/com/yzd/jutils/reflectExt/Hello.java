package com.yzd.jutils.reflectExt;

/***
 *
 *
 * @author yzd
 * @date 2018/9/17 17:57.
 */

public class Hello{

    private String name ;

    public String helloStrs(){
        //根据不同的方法名称，调用不用的方法
        System.out.println("hello1");
        return "hello1";
    }

    public void helloStrs(String a) {
        //根据不同的方法名称，调用不用的方法
        System.out.println("hello11");
    }

    public void helloStrs2(String a ,Integer b) throws Exception{
        //根据不同的方法名称，调用不用的方法
        System.out.println("hello12");
    }
}