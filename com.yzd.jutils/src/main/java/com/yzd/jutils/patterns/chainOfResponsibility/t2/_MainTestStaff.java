package com.yzd.jutils.patterns.chainOfResponsibility.t2;

/***
 *
 *
 * Created by yzd on 2018/9/11 17:53.
 */

public class _MainTestStaff {
    //参考：设计模式之职责链
    //http://blog.51cto.com/zero01/2065240
    public static void main(String[] args) {

        // 实例化各个管理者对象
        Manager director = new Director("主管");
        Manager branchManager = new BranchManager("部门经理");
        Manager cho = new CHO("人力资源总监");
        Manager generalManager = new GeneralManager("总经理");

        // 设置上级与下级
        director.setSuperior(branchManager);
        branchManager.setSuperior(cho);
        cho.setSuperior(generalManager);

        director.raisesRequest("加薪", 100);
        director.raisesRequest("加薪", 300);
        director.raisesRequest("加薪", 600);
        director.raisesRequest("加薪", 1000);
        director.raisesRequest("加薪", 1500);

    }
}
