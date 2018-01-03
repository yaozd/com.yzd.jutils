package com.yzd.jutils.annotationExt.lockExt;

public enum BaodanLockEnum {
    HK("HK");
    BaodanLockEnum(String name){
        this.name=name;
    }
    private String name;
    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
