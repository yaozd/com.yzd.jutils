package com.yzd.jutils.Excel;

import com.yzd.jutils.annotationExt.Value;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class ExcelMapToModel {
    /**
     * 通过ExcelUtil把Excel转为map的形式，然后再把map转为tableModel的形式
     *
     * @param args
     */
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("承保止期", "06-二月-2018");
        //
        TableModel tableModel = mapToTableModel(map);
        System.out.println("name: " + tableModel.getName());
        //
        TableModel tableModel2 = mapToTableModel2(TableModel.class, map);
        System.out.println("name: " + tableModel2.getName());
    }

    private static TableModel mapToTableModel(Map<String, String> map) throws IllegalAccessException, InvocationTargetException {
        TableModel tableModel = new TableModel();
        Method[] methods = TableModel.class.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(Value.class);
            if (hasAnnotation) {
                Value value = method.getAnnotation(Value.class);
                if (map.containsKey(value.value())) {
                    method.invoke(tableModel, map.get(value.value()));
                }
            }
        }
        return tableModel;
    }

    /***
     * 通过ExcelUtil把Excel转为map的形式，然后再把map转为tableModel的形式
     * @param clazz
     * @param map
     * @param <T>
     * @return
     */
    private static <T> T mapToTableModel2(Class<T> clazz, Map<String, String> map) {
        T tableModel = null;
        try {
            tableModel = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            boolean hasAnnotation = method.isAnnotationPresent(Value.class);
            if (hasAnnotation) {
                Value value = method.getAnnotation(Value.class);
                if (map.containsKey(value.value())) {
                    try {
                        method.invoke(tableModel, map.get(value.value()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return tableModel;
    }
}
