package com.yzd.jutils.patterns.chainOfResponsibility.t2;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:51.
 */

public class BranchManager extends Manager{

    public BranchManager(String position) {
        super(position);
    }

    @Override
    public void raisesRequest(String requestName, int requestNumber) {

        // 有权处理300元以内的加薪
        if (requestName.equals("加薪") && requestNumber <= 300) {
            System.out.println(position + "：ok，批准了");
        } else if (superior != null) {

            // 将请求传递给上级
            superior.raisesRequest(requestName, requestNumber);
        }
    }
}
