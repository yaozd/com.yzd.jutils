package com.yzd.jutils.patterns.chainOfResponsibility.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:46.
 */

public class ConcreteHandler2 extends Handler {

    @Override
    public void handlerRequest(int request) {
        // 处理10-20之间的请求
        if (request >= 10 && request <= 20) {
            System.out.println("ConcreteHandler2处理请求" + request);
        } else if (successor != null) {
            // 将请求传递到下一位
            successor.handlerRequest(request);
        }
    }
}
