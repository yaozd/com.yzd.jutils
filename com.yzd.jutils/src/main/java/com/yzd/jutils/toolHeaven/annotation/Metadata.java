package com.yzd.jutils.toolHeaven.annotation;

import java.lang.annotation.*;

/**
 * 元注解
 *
 * @author binbin.hou
 * @since 0.1.3
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.ANNOTATION_TYPE)
public @interface Metadata {
}
