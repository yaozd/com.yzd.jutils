package com.yzd.jutils.JWTExt2.t1;

public interface CurrentTokenEnum {
    /**
     * T:数据类型
     * 通过不同的类型，页面做出相应业务逻辑处理
     */
    enum DataType {
        login(1, "登录情况下TOKEN"),
        noLogin(0, "非登录情况下TOKEN");

        public final Integer CODE;

        DataType(int code, String desc) {
            this.CODE = code;
        }
    }

    /**
     * V:数据结构版本
     */
    enum DataVersion {
        v1(1, "版本1");

        public final Integer CODE;

        DataVersion(int code, String desc) {
            this.CODE = code;
        }
    }
}
