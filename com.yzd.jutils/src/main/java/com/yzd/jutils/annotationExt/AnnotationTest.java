package com.yzd.jutils.annotationExt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class AnnotationTest {
    /***
     * java使用自定义注解进行赋值
     * https://zhidao.baidu.com/question/936591502766703372.html
     * @param args
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        User user = new User();
        Method[] methods = User.class.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(Value.class);
            if (hasAnnotation) {
                Value value = method.getAnnotation(Value.class);
                method.invoke(user, value.value());
            }
        }
        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());
        //使用lombok的方式不能通过自定义注解进行赋值
        t1();
    }

    /**
     * 使用lombok的方式不能通过自定义注解进行赋值
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void t1() throws IllegalAccessException, InvocationTargetException {
        Person user = new Person();
        Method[] methods = Person.class.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(Value.class);
            if (hasAnnotation) {
                Value value = method.getAnnotation(Value.class);
                method.invoke(user, value.value());
            }
        }
        System.out.println("name: " + user.getName());
        System.out.println("age: " + user.getAge());
    }
}
