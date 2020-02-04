package com.yzd.jutils.annotationExt.redisCacheExt;

import com.google.common.base.Preconditions;
import com.yzd.jutils.encrypt.MD5Util;
import com.yzd.jutils.fastjson.FastJsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Aspect
@Component
public class RedisCacheAspect {

    @Pointcut("@annotation(RedisCache)")
    public void setJoinPoint() {
    }

    //环绕通知:可以获取返回值
    @Around(value = "setJoinPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {

        Object result = null;
        try {
            //获得请求参数，目前缓存方法只接受一个请求参数
            Object[] args = getRequestArgs(proceedingJoinPoint);
            Preconditions.checkArgument(args.length < 2, "缓存方法只接受一个请求参数");
            Object where = args.length == 0 ? null : args[0];
            //获得缓存方法的返回值类型
            Method method = getMethod(proceedingJoinPoint);
            Type returnType = getReturnType(method);
            Preconditions.checkArgument(!"void".equalsIgnoreCase(returnType.getTypeName()), "缓存方法的返回值类型不能是void；当前方法路径：" + method.toString());
            //key策略：KEY_NAME+WHERE_MD5(数据结构版本+请求参数)
            //获得当前方法的注解信息
            RedisCache methodCache = getAnnotation(method, RedisCache.class);
            //1,通过keyName获得key的缓存配置信息
            String keyName = methodCache.key();
            //2,
            String contentOfMD5 = "数据结构版本" + "|" + FastJsonUtil.serialize(where);
            //3,
            String whereMD5 = MD5Util.getMd5(contentOfMD5, "utf-8");
            //缓存的具体执行
            String keyFullName = keyName + "#" + whereMD5;
            //1，查询缓存
            //2，执行方法
            result = proceedingJoinPoint.proceed();
            //3，缓存至Redis

        } catch (Throwable e) {
            //异常
            throw new IllegalStateException(e);
        }
        //返回结果
        return result;
    }

    //
    private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        Type t = method.getAnnotatedReturnType().getType();
        System.out.println(t);

        //当前是缓存的方法，所以返回结果不能是void
        if ("void".equalsIgnoreCase(t.getTypeName())) {
            throw new IllegalStateException("该注解下的方法返回值类型不能是：void,当前方法路径：" + method.toString());
        }
        return method.getAnnotation(clazz);
    }

    //通过keyName获得key的缓存配置信息
    //获得当前方法的请求参数
    private Object[] getRequestArgs(ProceedingJoinPoint jp) {
        Object[] args = jp.getArgs();
        return args;
    }

    //获得当前方法
    private Method getMethod(ProceedingJoinPoint jp) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method;
    }

    //获得当前方法的注解信息
    private <T extends Annotation> T getAnnotation(Method method, Class<T> clazz) {
        return method.getAnnotation(clazz);
    }

    //获得当前方法的返回值类型
    private Type getReturnType(Method method) {
        Type t = method.getAnnotatedReturnType().getType();
        return t;
    }

    //当前方法路径：public void com.yzd.jutils.annotationExt.redisCacheExt.UserService.empty(java.lang.Integer)
    private String getMethodPath(Method method) {
        return method.toString();
    }
}