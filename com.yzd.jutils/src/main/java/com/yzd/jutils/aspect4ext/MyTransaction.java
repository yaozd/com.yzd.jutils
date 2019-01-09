package com.yzd.jutils.aspect4ext;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface MyTransaction {
    /**
     * 当前执行步步骤-代码
     * @return
     */
    int stepStatus();

    /**
     * 当前执行步步骤-名称
     * @return
     */
    String stepName();
}
