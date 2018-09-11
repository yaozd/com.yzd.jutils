package com.yzd.jutils.patterns.chainOfResponsibility.t2;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:52.
 */

public class GeneralManager extends Manager{

    public GeneralManager(String position) {
        super(position);
    }

    @Override
    public void raisesRequest(String requestName, int requestNumber) {

        // 有权处理1000元以上的加薪
        if (requestName.equals("加薪") && requestNumber <= 1000) {
            System.out.println(position + "：ok，批准了");
        }else {
            // 1000元以上看心情
            System.out.println(position + "：emmm我考虑一下");
        }
    }
}
