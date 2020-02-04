package com.yzd.jutils.toolHeaven.reflect.handler;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.simple.SimpleAnnotation;
import com.yzd.jutils.toolHeaven.support.handler.IHandler;

import java.lang.annotation.Annotation;

/**
 * 默认的注解实现类
 *
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SimpleAnnotationHandler implements IHandler<Annotation, SimpleAnnotation> {

    @Override
    public SimpleAnnotation handle(Annotation annotation) {
        SimpleAnnotation simpleAnnotation = new SimpleAnnotation();
        simpleAnnotation.type(annotation.annotationType());
        return simpleAnnotation;
    }

}
