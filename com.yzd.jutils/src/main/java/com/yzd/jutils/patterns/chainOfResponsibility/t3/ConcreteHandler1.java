package com.yzd.jutils.patterns.chainOfResponsibility.t3;

import com.yzd.jutils.fastjson.FastJsonUtil;

/***
 *
 *
 * Created by yzd on 2018/9/12 9:53.
 */

public class ConcreteHandler1 extends Handler {
    @Override
    public void handlerRequest(BusinessData request) {
        if (request.getStatus()==BusinessStatus.保存) {
            //处理“保存”状态下的业务逻辑
            System.out.println("ConcreteHandler1处理请求" + FastJsonUtil.serialize(request));
            //调整状态为下一个继承者的状态，与业务请求数据
            request.setStatus(BusinessStatus.提交);
            request.setDataJson("提交的请求数据");
            successor.handlerRequest(request);
        } else if (successor != null) {
            // 将请求传递到下一位
            successor.handlerRequest(request);
        }
    }
}
