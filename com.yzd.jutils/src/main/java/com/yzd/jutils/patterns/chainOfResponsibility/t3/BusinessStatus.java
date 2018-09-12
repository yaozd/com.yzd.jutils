package com.yzd.jutils.patterns.chainOfResponsibility.t3;

/***
 *
 *
 * Created by yzd on 2018/9/12 9:43.
 */

public enum BusinessStatus {
    保存(1),
    提交(2),
    审核(3),
    拒绝(4),
    预发布(5),
    上架(6),
    下架(7),
    删除(8),
    完成(9);;
    private int id;

    BusinessStatus(int id) {
        this.id = id;
    }
}
