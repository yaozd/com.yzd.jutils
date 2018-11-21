package com.yzd.jutils.dubboExt.sameDBMutilInterface;

import org.junit.Test;

/**
 * @author zd.yao
 * @description
 * @date 2018/11/21
 **/

public class _TestMain {
    /***
     * 同一个数据库，部署前台API与后台管理两个相同的dubbo服务
     * 可以在生产中互不影响，减少相互依赖关系。如：发版后影响前后台同时均不能使用
     * 解决方案：使用一个类可以实现多个接口的方式。
     */
    @Test
    public void test1(){
        ParentInf t1=new ItemImpl();
        t1.a();
        ParentInf t2=new ItemImpl();
        t2.a();
        ParentInf t3=new ItemImpl();
        t3.a();
    }
}
