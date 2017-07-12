package com.yzd.jutils.springBoot.aop;

import org.springframework.stereotype.Component;

/**
 * Created by zd.yao on 2017/7/6.
 */
@Component
public class Person {
    @NoThrowExceptionAnnMQ
    public void t1(){
        Integer a=1/0;
    }
}
