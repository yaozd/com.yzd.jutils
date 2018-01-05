package com.yzd.jutils.annotationExt.lockExt;

/**
 * 自定义异常
 */
public class BaodanLockException extends RuntimeException {
    public BaodanLockException(String message)
   {
       super(message);
   }
}
