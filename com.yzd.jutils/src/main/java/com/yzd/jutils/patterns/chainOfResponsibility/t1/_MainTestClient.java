package com.yzd.jutils.patterns.chainOfResponsibility.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:48.
 */

public class _MainTestClient {
    //参考：设计模式之职责链
    //http://blog.51cto.com/zero01/2065240
    public static void main(String[] args) {

        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();
        // 设置职责链的上家与下家，h1作为链头
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        int[] numbers = {10, 20, 30, 51, 25, 5, 8, 7, 15, 47};

        for (int i : numbers) {
            h1.handlerRequest(i);
        }
    }
}
