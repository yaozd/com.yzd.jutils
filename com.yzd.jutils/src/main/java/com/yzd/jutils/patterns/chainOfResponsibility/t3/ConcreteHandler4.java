package com.yzd.jutils.patterns.chainOfResponsibility.t3;

import com.yzd.jutils.fastjson.FastJsonUtil;

/***
 *
 *
 * Created by yzd on 2018/9/12 10:07.
 */

public class ConcreteHandler4 extends Handler {
    @Override
    public void handlerRequest(BusinessData request) {
        if (request.getStatus() == BusinessStatus.完成) {
            //处理“审核”状态下的业务逻辑
            System.out.println("ConcreteHandler1处理请求" + FastJsonUtil.serialize(request));
            System.out.println("业务处理完成！");
            return;
        }
        throw new IllegalArgumentException("请求数据不正确。reuest=" + FastJsonUtil.serialize(request));
    }
}
