package com.yzd.jutils.enumExt;

/**
 * Created by zd.yao on 2017/6/13.
 */

/**
 * 是否有效状态
 */
public enum IsDelEnum {
    有效(1), 无效(2);
    private int value;

    private IsDelEnum(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public static IsDelEnum getEnum(int index) {
        for (IsDelEnum c : IsDelEnum.values()) {
            if (c.getValue() == index) {
                return c;
            }
        }
        return null;
    }
}