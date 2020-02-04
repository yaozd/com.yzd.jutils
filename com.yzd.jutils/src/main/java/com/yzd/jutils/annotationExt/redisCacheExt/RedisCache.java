package com.yzd.jutils.annotationExt.redisCacheExt;

import java.lang.annotation.*;

/**
 * 自定义注解，结合AOP实现Redis自动缓存
 *
 * @Target(ElementType.METHOD):该注解只能用于方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface RedisCache {
    String key();
}
