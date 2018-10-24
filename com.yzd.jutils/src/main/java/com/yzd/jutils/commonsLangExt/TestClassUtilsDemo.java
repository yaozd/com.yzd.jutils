package com.yzd.jutils.commonsLangExt;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

import java.util.Date;

public class TestClassUtilsDemo {
    @Test
    public void t1(){
        System.out.println ("获取类实现的所有接口.");
        System.out.println (ClassUtils.getAllInterfaces (Date.class) );

        System.out.println ("获取类所有父类.");
        System.out.println (ClassUtils.getAllSuperclasses (Date.class) );

        System.out.println ("获取简单类名.");
        System.out.println (ClassUtils.getShortClassName (Date.class) );

        System.out.println ("获取包名.");
        System.out.println (ClassUtils.getPackageName (Date.class) );

        System.out.println ("判断是否可以转型.");
        System.out.println (ClassUtils.isAssignable (Date.class, Object.class) );
        System.out.println (ClassUtils.isAssignable (Object.class, Date.class) );

    }
}
