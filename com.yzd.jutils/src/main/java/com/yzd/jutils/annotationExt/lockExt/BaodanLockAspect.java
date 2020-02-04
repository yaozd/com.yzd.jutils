package com.yzd.jutils.annotationExt.lockExt;

import com.google.common.base.Preconditions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class BaodanLockAspect {
    @Pointcut("@annotation(BaodanLock)")
    public void setJoinPoint() {
    }

    //环绕通知:可以获取返回值
    @Around(value = "setJoinPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        Object[] args = getRequestArgs(proceedingJoinPoint);
        Preconditions.checkArgument(args.length < 3, "缓存方法只接受一个请求参数");
        IdForBaodanLock lock = (IdForBaodanLock) args[0];
        Preconditions.checkNotNull(lock, "保单锁不能为null");
        Preconditions.checkNotNull(lock.getId(), "保单锁ID不能为空");
        Preconditions.checkArgument(lock.getId().length() > 5, "保单锁ID长度必须大于5，当前lock.getId()=[" + lock.getId() + "]");
        BaodanLock baodanLock = getAnnotation(proceedingJoinPoint, BaodanLock.class);
        String name = baodanLock.key().name();
        try {
            //互斥锁命名规则：
            //项目编号+方法名+XX公司缩写+“：”+XX单号
            //写入互斥锁
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            //异常
            throw new IllegalStateException(e);
        } finally {
            //删除互斥锁
        }
        //返回结果
        return result;
    }

    private Object[] getRequestArgs(ProceedingJoinPoint jp) {
        Object[] args = jp.getArgs();
        return args;
    }

    //获得当前方法的注解信息
    private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method.getAnnotation(clazz);
    }
}
