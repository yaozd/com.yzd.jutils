package com.yzd.jutils.JWTExt2.t1;

/**
 * JWT 验证异常
 */
public class CustomJwtException extends RuntimeException {
    public CustomJwtException(String msg) {
        super(msg);
    }

    public CustomJwtException() {
        super();
    }
}
