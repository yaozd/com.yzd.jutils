package com.yzd.jutils.patterns.singleton;

import com.yzd.jutils.print.PrintUtil;

/**
 * todo 单例- 静态内部类
 * Created by zd.yao on 2017/4/28.
 */
// Effective Java 第一版推荐写法
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private Singleton (){
        //todo do something
        PrintUtil.outLn("init");
    }
}
