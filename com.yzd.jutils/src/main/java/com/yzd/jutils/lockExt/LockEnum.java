package com.yzd.jutils.lockExt;

/**
 * Created by zd.yao on 2017/10/16.
 */
public enum LockEnum {
    Product("productLock");
    private String name;
    LockEnum(String name){
        this.name=name;
    }
}
