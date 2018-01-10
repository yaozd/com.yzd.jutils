package com.yzd.jutils.enumExt;

import org.omg.CosNaming.NameHelper;

import java.util.HashMap;
import java.util.Map;

public enum NameEnum2 {
    蓝色(1, "blue"),
    绿色(2, "green"),
    红色(3, "red"),
    橙色(4, "orange"),
    粉色(5, "pink");

    // 成员变量
    private String name;
    private Integer index;
    private static final Map<Integer,String> nameMap= EnumHelper.getNameMap(NameEnum2.class,"getName","getValue");

    // 构造方法
    private NameEnum2(int index, String name) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        return nameMap.get(index);
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
