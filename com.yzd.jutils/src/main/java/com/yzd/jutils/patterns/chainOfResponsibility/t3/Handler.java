package com.yzd.jutils.patterns.chainOfResponsibility.t3;

/***
 *
 *
 * Created by yzd on 2018/9/12 9:50.
 */

public abstract class Handler {
    // 设置继任者
    protected Handler successor;

    // 设置继任者
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handlerRequest(BusinessData request);
}
