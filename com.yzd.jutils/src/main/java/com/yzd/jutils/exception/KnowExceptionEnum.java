package com.yzd.jutils.exception;

/**
 * 已知异常
 * @Author: yaozh
 * @Description:
 */
public enum KnowExceptionEnum {
    ERROR1(10, "error1");

    private final int innerCode;
    private final String message;

    KnowExceptionEnum(int innerCode, String message) {
        this.innerCode = innerCode;
        this.message = message;
    }

    public int getInnerCode() {
        return innerCode;
    }

    public String getMessage() {
        return message;
    }
}
