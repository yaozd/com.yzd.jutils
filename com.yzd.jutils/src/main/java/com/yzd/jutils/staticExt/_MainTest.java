package com.yzd.jutils.staticExt;

/**
 * 是按照静态变量从上而下，进行实例化后加载到内存中的
 * 所以静态变量的位置与顺序不同，输出的结果也不同
 * 所有static标识的都是按照先后的顺序执行的。
 * Created by zd.yao on 2017/9/8.
 */
public class _MainTest {
    //
    //int a=110;
    //static int b=112;
    //
    public static void main(String[] args){
        staticFunction();
    }
    //位置不同输出的结果也不相同
    static _MainTest st=new _MainTest();
    //
    static {
        System.out.println("1");
    }
    //
    //static _MainTest st=new _MainTest();
    {
        System.out.println("2");
    }
    _MainTest(){
        System.out.println("3");
        System.out.println("a="+a+",b="+b);
    }
    //此代码块的执行会优于构造函数（_MainTest）的执行
    {
        System.out.println("5");
    }
    public static void staticFunction(){
        System.out.println("4");
    }
    //输出结果
    /*
    2
    3
    a=110,b=0
    1
    */
    int a=110;
    static int b=112;
}
