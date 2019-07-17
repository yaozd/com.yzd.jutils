package com.yzd.jutils.autowiredExtJson.model;

/**
 * 配置对象的类型与数据版本号
 */
public enum ModelEnum {
    CatModelE(1, 1);
    private final int modelVersion;
    private final int modelType;

    ModelEnum(int modelType, int modelVersion) {
        this.modelType = modelType;
        this.modelVersion = modelVersion;
    }

    public int getModelVersion() {
        return modelVersion;
    }

    public int getModelType() {
        return modelType;
    }
}
