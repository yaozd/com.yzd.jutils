package com.yzd.jutils.enumExt.ext2;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TbEnumExtend {
    /**
     * 通过反射的方式获得CODE值的集合
     * @param clazz
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> Set<Integer> getCodeList(Class<T> clazz, String fieldName) {
        Set<Integer> codeSet=new HashSet<>();
        try {
            T[] arr = clazz.getEnumConstants();
            Field field = clazz.getDeclaredField(fieldName);
            for (T entity : arr) {
                Integer code = (Integer) field.get(entity);
                codeSet.add(code);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //不可变集合
        return Collections.unmodifiableSet(codeSet);
    }
    public static <T extends Enum<T>> Set<Integer> getCodeList(Class<T> clazz){
        return getCodeList(clazz,"CODE");
    }
}
