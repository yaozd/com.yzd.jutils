package com.yzd.jutils.loadBalance;

import com.yzd.jutils.timeExt.MethodExecuteTimeUtils;
import com.yzd.jutils.timeExt.TestMethod;

/**
 * Created by zd.yao on 2017/7/4.
 */
public class Test {
    /**
     * 几种简单的负载均衡算法及其Java代码实现
     * http://www.cnblogs.com/szlbm/p/5588555.html
     */
    public static void main(String[] args) {
        MethodExecuteTimeUtils tool = new MethodExecuteTimeUtils();
        tool.testTimeByNS(new TestMethod() {
            //定义execute方法
            public void execute() {
                //这里可以加放一个或多个要测试运行时间的方法
                //for(int i=0;i<10000;i++)
                RoundRobin.getServer();
            }
        });
        tool.testTimeByMS(new TestMethod() {
            //定义execute方法
            public void execute() {
                //轮询（Round Robin）法-- 改进版
                //这里可以加放一个或多个要测试运行时间的方法
                for (int i = 0; i < 10000000; i++)
                    RoundRobin2.getServer();
            }
        });
        System.out.print(RoundRobin.getServer());
    }

    @org.junit.Test
    public void CacheRoundRobin_T1() {

        for (int i = 0; i < 100; i++) {
            Long value = CacheRoundRobin.getInstance().getValue("P01.Other1SelectAll");
            //如果对"主名=P01.Other1SelectAll"的KEY进行水平扩展5倍的话就需要对5进行取余
            //取余结果是 0，1，2，3，4；
            //然后将编号放在KEY名称的后面
            //P01.Other1SelectAll:1di41n95bd34:0848e79ab3873df627ab8e1d3e13a61a 转化为
            //P01.Other1SelectAll:1di41n95bd34:0848e79ab3873df627ab8e1d3e13a61a.0;P01.Other1SelectAll:1di41n95bd34:0848e79ab3873df627ab8e1d3e13a61a.1
            //P01.Other1SelectAll:1di41n95bd34:0848e79ab3873df627ab8e1d3e13a61a.2;P01.Other1SelectAll:1di41n95bd34:0848e79ab3873df627ab8e1d3e13a61a.3
            //P01.Other1SelectAll:1di41n95bd34:0848e79ab3873df627ab8e1d3e13a61a.4
            //这样就水平扩展5倍。
            Long t1 = value % 5;
            System.out.println(value);
            System.out.println("t1=" + t1);
        }
    }
}
