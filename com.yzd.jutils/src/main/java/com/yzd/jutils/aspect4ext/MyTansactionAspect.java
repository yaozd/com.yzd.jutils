package com.yzd.jutils.aspect4ext;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class MyTansactionAspect {


    @Pointcut("@annotation(MyTransaction)")
    public void setJoinPoint() {
        throw new UnsupportedOperationException();
    }

    @Around(value = "setJoinPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Method method = AspectUtil.getMethod(proceedingJoinPoint);
        MyTransaction myTransaction = AspectUtil.getAnnotation(method, MyTransaction.class);
        Object[] requestArgs = AspectUtil.getRequestArgs(proceedingJoinPoint);
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }
}