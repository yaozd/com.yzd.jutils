package com.yzd.jutils.fastjsonFilter;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏字段注解
 *
 * @author worstEzreal
 * @version V1.0.0
 * @date 2017/7/19
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveLogInfo {
    SensitiveLogType type();
}