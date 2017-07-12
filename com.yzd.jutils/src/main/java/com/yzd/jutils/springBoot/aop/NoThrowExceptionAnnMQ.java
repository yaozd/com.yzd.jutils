package com.yzd.jutils.springBoot.aop;

import java.lang.annotation.*;

/**
 * Created by zd.yao on 2017/7/6.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoThrowExceptionAnnMQ {
}