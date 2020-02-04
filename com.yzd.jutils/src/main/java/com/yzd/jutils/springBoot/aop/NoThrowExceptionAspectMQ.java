package com.yzd.jutils.springBoot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zd.yao on 2017/7/6.
 */
@Aspect
@Configuration
public class NoThrowExceptionAspectMQ {
    @Pointcut("@annotation(com.yzd.jutils.springBoot.aop.NoThrowExceptionAnnMQ)")
    public void NoThrowExceptionAspect() {
    }

    @Around("NoThrowExceptionAspect()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            pjp.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
