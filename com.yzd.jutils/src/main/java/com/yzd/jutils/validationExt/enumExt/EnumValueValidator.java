package com.yzd.jutils.validationExt.enumExt;

/**
 * @author: yaozhendong
 * @create: 2019-09-25 13:10
 **//*


import com.hualala.infrastructure.hyperspace.console.exceptions.ArgumentValidException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Integer> {
    private Class<? extends Enum> enumClass;
    private static final String METHOD_NAME = "toEnum";

    //这个方法做一些初始化校验
    public void initialize(EnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.value();
        try {
            // 先判断该enum是否实现了toEnum方法
            enumClass.getDeclaredMethod(METHOD_NAME, int.class);
        } catch (NoSuchMethodException e) {
            throw new ArgumentValidException("the enum class has not toEnum method,Enum Class=" + enumClass);
        }
    }

    // 这个方法写具体的校验逻辑：校验数字是否属于指定枚举类型的范围
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        Method declareMethod;
        try {
            declareMethod = enumClass.getDeclaredMethod(METHOD_NAME, int.class);
        } catch (NoSuchMethodException e) {
            return false;
        }
        try {
            Object result = declareMethod.invoke(null, value);
            if (result == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}*/
