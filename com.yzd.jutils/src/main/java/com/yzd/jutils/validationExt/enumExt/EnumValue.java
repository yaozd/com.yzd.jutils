package com.yzd.jutils.validationExt.enumExt;

/**
 * 自定义注解进行参数校验-以校验枚举值是否合法
 *
 * @author: yaozhendong
 * @create: 2019-09-25 13:11
 **/

/*
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValueValidator.class})// 与约束注解关联的校验器
public @interface EnumValue {
    // 默认错误消息
    String message() default " the integer is not one of the enum values";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 约束注解的有效负载
    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum> value();

    // 同时指定多个时使用
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        EnumValue[] value();
    }
}

*/
