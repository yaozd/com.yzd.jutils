package com.yzd.jutils.enumExt;

import java.util.HashMap;
import java.util.Map;

public enum NameEnum {
    蓝色(1, "blue"),
    绿色(2, "green"),
    红色(3, "red"),
    橙色(4, "orange"),
    粉色(5, "pink");

    // 成员变量
    private String name;
    private Integer index;
    private static final Map<Integer, String> nameMap = getNameMap();

    // 通过hashMap提高执行效率
    // 主要场景是在频繁转换时使用
    private static Map<Integer, String> getNameMap() {
        Map<Integer, String> map = new HashMap<>();
        for (NameEnum c : NameEnum.values()) {
            map.put(c.index, c.name);
        }
        return map;
    }

    // 构造方法
    private NameEnum(int index, String name) {
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
