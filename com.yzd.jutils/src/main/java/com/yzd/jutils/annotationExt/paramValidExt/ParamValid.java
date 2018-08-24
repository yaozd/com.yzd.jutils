package com.yzd.jutils.annotationExt.paramValidExt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *
 *
 * Created by yzd on 2018/8/23 16:17.
 */

//此注解只能使用在请求参数上
//@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValid {
}