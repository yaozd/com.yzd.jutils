package com.yzd.jutils.annotationExt.lockExt;

/**
 * 通过枚举作为：以注解形式的缓存key的一部分可以知道，用那些地方使用了此枚举
 * 枚举的name属性可以确定KEY的唯一性。
 */
public enum BaodanLockEnum {
    HK("描述信息");
    BaodanLockEnum(String desc){
        this.desc=desc;
    }
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    // get set 方法

}
