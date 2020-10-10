package com.yzd.jutils.exception;

/**
 * 已知运行时异常
 * 异常模板参考
 *
 * @Author: yaozh
 * @Description:
 */
public class KnowRuntimeException extends RuntimeException {


    private final int code;
    private final String message;

    public KnowRuntimeException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public KnowRuntimeException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
