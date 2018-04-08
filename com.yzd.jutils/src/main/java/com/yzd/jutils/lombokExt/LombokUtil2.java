package com.yzd.jutils.lombokExt;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LombokUtil2 {
    //toString 转为 map
    public static Map<String,String> stringToBean(String str){
        Map<String,String> paraMap=new HashMap<String,String>();
        String[] para=str.split(",");
        for(int i=0,len=para.length;i<len;i++){
            String[] temp=para[i].split("=");
            paraMap.put(temp[0].trim(), temp[1]);
        }
        return paraMap;
    }
    //map 转为bean
    public static Object convertMap(Class<?> type, Map<?, ?> map) {

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            //logger.error("分析类属性失败");
            e.printStackTrace();
        } // 获取类属性
        Object obj = null;
        try { // 创建 JavaBean 对象
            obj = type.newInstance();
        } catch (InstantiationException e) {
            //logger.error("实例化 JavaBean 失败");
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            //logger.error("实例化 JavaBean 失败");
            e.printStackTrace();
        }

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                try {
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (IllegalAccessException  e) {
                    //logger.error("实例化 JavaBean 失败");
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    //logger.error("实例化 JavaBean 失败");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    //logger.error("调用属性的 setter 方法失败");
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
