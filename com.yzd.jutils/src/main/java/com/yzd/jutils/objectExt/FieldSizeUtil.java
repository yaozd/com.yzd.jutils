package com.yzd.jutils.objectExt;

import org.apache.lucene.util.RamUsageEstimator;

import java.lang.reflect.Field;

public class FieldSizeUtil {
    /**
     * @param o 操作对象
     * @param c 操作类。用于获取类中的方法
     * @return
     * @MethodName : getString
     * @Description : 获取类中全部属性及属性值
     */
    public static String getString(Object o, Class<?> c) {
        String result = c.getSimpleName() + ":";
        // 获取父类。推断是否为实体类
        if (c.getSuperclass().getName().indexOf("entity") >= 0) {
            result += "\n<" + getString(o, c.getSuperclass()) + ">,\n";
        }
        // 获取类中的全部定义字段
        Field[] fields = c.getDeclaredFields();
        // 循环遍历字段，获取字段相应的属性值
        for (Field field : fields) {
            // 假设不为空。设置可见性，然后返回
            field.setAccessible(true);
            try {
                // 设置字段可见，就可以用get方法获取属性值。
                result += field.getName() + "=" + field.get(o) +";size:"+ RamUsageEstimator.humanSizeOf(field.get(o))+ ",\n";
            } catch (Exception e) {
                // System.out.println("error--------"+methodName+".Reason is:"+e.getMessage());
            }
        }
        if (result.indexOf(",") >= 0) result = result.substring(0, result.length() - 2);
        return result;
    }
}
