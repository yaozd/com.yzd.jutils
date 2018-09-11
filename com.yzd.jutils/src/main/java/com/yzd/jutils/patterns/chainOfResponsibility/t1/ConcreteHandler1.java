package com.yzd.jutils.patterns.chainOfResponsibility.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:45.
 */

public class ConcreteHandler1 extends Handler {

    @Override
    public void handlerRequest(int request) {
        // 处理0-10之间的请求
        if (request >= 0 && request <= 10) {
            System.out.println("ConcreteHandler1处理请求" + request);
        } else if (successor != null) {
            // 将请求传递到下一位
            successor.handlerRequest(request);
        }
    }
}
