package com.yzd.jutils.reflectExt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***
 *
 *
 * @author yzd
 * @date 2018/9/17 17:56.
 */

public class HelloWord {

    //java 反射 根据不同方法，不同参数动态调用方法
    //http://lingyun246.iteye.com/blog/2320402
    //Java反射机制（三）：调用对象的私有属性和方法
    //https://blog.csdn.net/zuiwuyuan/article/details/39665269
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Hello h=new Hello();
            Object[] argspara=new Object[]{};
            Object aa = HelloWord.invokeMethod(h,"helloStrs",argspara);
            System.out.println("*******"+aa.toString()+"*******");

            HelloWord.invokeMethod(h, "helloStrs",argspara);
            argspara=new Object[]{"he"};
            HelloWord.invokeMethod(h, "helloStrs",argspara);
            argspara=new Object[]{"she",2};
            HelloWord.invokeMethod(h, "helloStrs2",argspara);
            //调用对象的私有属性
            Field nameField = null;
            nameField = Hello.class.getDeclaredField("name"); // 取得name属性
            nameField.setAccessible(true); // 此属性对外部可见
            nameField.set(h, "沉缘"); // 设置name属性内容
            System.out.println("姓名：" + nameField.get(h));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射调用方法
     * @param newObj 实例化的一个对象
     * @param methodName 对象的方法名
     * @param args 参数数组
     * @return 返回值
     * @throws Exception
     */
    public static Object invokeMethod(Object newObj, String methodName, Object[] args)throws Exception {
        Class ownerClass = newObj.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(newObj, args);
    }

}

