package com.yzd.jutils.switchExt;

public enum FruitsEnum {
    苹果("苹果"),
    梨("梨"),
    西瓜("西瓜"),
    香蕉("香蕉");
    private final String desc;

    FruitsEnum(String desc) {
        this.desc = desc;
    }

    public static Boolean isFruits(String name) {
        for (FruitsEnum item : FruitsEnum.values()) {
            if (item.desc.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
