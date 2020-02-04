package com.yzd.jutils.enumExt;

/**
 * Created by zd.yao on 2017/6/13.
 */
public enum ColorEnum {
    蓝色(1, "blue"),
    绿色(2, "green"),
    红色(3, "red"),
    橙色(4, "orange"),
    粉色(5, "pink");

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private ColorEnum(int index, String name) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ColorEnum c : ColorEnum.values()) {
            if (c.getValue() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return index;
    }

    public void setValue(int index) {
        this.index = index;
    }

}
