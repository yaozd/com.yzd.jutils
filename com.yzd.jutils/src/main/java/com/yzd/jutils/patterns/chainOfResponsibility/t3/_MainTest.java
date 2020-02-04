package com.yzd.jutils.patterns.chainOfResponsibility.t3;

/***
 *
 *
 * Created by yzd on 2018/9/12 10:02.
 */

public class _MainTest {
    //### 职责链与枚举状态组合可构建一个流程组合-实现“工作流引擎”
    //参考：设计模式之职责链
    //http://blog.51cto.com/zero01/2065240
    public static void main(String[] args) {
        //因为电商业务流程比较长，程序在执行时，任何一个阶段都可能会出现中断
        //通过职责链把整个业务流程串联在一起，形成一个完整的流程。
        //职责链使用的前提是要有一个分布式的事务日志做为依靠。
        //分布式的事务日志记录状态与请求数据。
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();
        Handler h4 = new ConcreteHandler4();
        // 设置职责链的上家与下家，h1作为链头
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);
        h3.setSuccessor(h4);

        BusinessData businessData = new BusinessData(BusinessStatus.提交, "当前业务阶段");
        h1.handlerRequest(businessData);
    }
}
