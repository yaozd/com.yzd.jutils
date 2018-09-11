package com.yzd.jutils.patterns.chainOfResponsibility.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:47.
 */

public class ConcreteHandler3 extends Handler {

    @Override
    public void handlerRequest(int request) {
        // 处理20-30之间的请求
        if (request >= 20 && request <= 30) {
            System.out.println("ConcreteHandler3处理请求" + request);
        } else if (successor != null) {
            // 将请求传递到下一位
            successor.handlerRequest(request);
        }
    }
}
