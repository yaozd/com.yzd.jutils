package com.yzd.jutils.toolHeaven.reflect.model;

import com.yzd.jutils.toolHeaven.util.lang.ObjectUtil;
import com.yzd.jutils.toolHeaven.util.util.Optional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 字段对象
 *
 * @author binbin.hou
 * @since 0.1.6
 */
public class FieldBean {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 反射字段
     */
    private Field field;

    /**
     * 注解对象
     */
    private Annotation annotation;

    public String name() {
        return name;
    }

    public FieldBean name(String name) {
        this.name = name;
        return this;
    }

    public Field field() {
        return field;
    }

    public FieldBean field(Field field) {
        this.field = field;
        return this;
    }

    public Annotation annotation() {
        return annotation;
    }

    public FieldBean annotation(Annotation annotation) {
        this.annotation = annotation;
        return this;
    }

    /**
     * 获取指定类型的注解信息
     *
     * @param tClass 注解类型
     * @param <T>    泛型
     * @return 结果
     */
    public <T extends Annotation> T annotationByType(final Class<T> tClass) {
        if (ObjectUtil.isNull(annotation)) {
            return null;
        }

        return (T) annotation;
    }

    /**
     * 获取指定类型的注解信息 Optional
     *
     * @param tClass 注解类型
     * @param <T>    泛型
     * @return 结果
     */
    public <T extends Annotation> Optional<T> annotationOptByType(final Class<T> tClass) {
        T t = this.annotationByType(tClass);
        return Optional.ofNullable(t);
    }
}
