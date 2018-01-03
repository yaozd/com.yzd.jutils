package com.yzd.jutils.annotationExt.lockExt;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface BaodanLock {
    BaodanLockEnum key();
}
