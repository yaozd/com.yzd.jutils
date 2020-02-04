package com.yzd.jutils.toolHeaven.annotation;

import java.lang.annotation.*;

/**
 * 指这个类将会被迁移到共有项目模块 heaven 模块
 *
 * @author binbin.hou
 * @since 0.0.6
 */
@Inherited
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonEager {
}
