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
    public static void main(String[] args){
        MethodExecuteTimeUtils tool = new  MethodExecuteTimeUtils();
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
}
