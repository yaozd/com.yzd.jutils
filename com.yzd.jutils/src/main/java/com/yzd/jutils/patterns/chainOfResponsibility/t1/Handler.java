package com.yzd.jutils.patterns.chainOfResponsibility.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:44.
 */

public abstract class Handler {

    protected Handler successor;

    // 设置继任者
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handlerRequest(int request);

}