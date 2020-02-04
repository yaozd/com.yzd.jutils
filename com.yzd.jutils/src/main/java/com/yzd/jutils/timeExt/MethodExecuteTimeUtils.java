package com.yzd.jutils.timeExt;

/**
 * 获取程序执行时间
 * Created by zd.yao on 2017/7/4.
 */
public class MethodExecuteTimeUtils {
    /**
     * 毫秒
     * 测试函数使用时间，通过定义testMethod接口的execute方法
     *
     * @param testMethod
     */
    public void testTimeByMS(TestMethod testMethod) {
        long begin = System.currentTimeMillis(); //测试起始时间
        testMethod.execute(); ///进行回调操作
        long end = System.currentTimeMillis(); //测试结束时间
        System.out.println("[use time]:" + (end - begin) + "ms"); //打印使用时间
    }

    /**
     * 纳秒
     *
     * @param testMethod
     */
    public void testTimeByNS(TestMethod testMethod) {
        long begin = System.nanoTime(); //测试起始时间
        testMethod.execute(); ///进行回调操作
        long end = System.nanoTime(); //测试结束时间
        System.out.println("[use time]:" + (end - begin) + "ns"); //打印使用时间
    }

    public static void main(String[] args) {
        MethodExecuteTimeUtils tool = new MethodExecuteTimeUtils();
        tool.testTimeByNS(new TestMethod() {
            //定义execute方法
            public void execute() {
                //这里可以加放一个或多个要测试运行时间的方法
                //TestObject.testMethod();
                System.out.println("具体方法");
            }
        });
    }
}
