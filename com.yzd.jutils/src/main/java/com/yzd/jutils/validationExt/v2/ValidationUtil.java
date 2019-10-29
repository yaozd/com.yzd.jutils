package com.yzd.jutils.validationExt.v2;

import javax.validation.*;
import javax.validation.groups.Default;
import java.util.*;

/**
 * 参数格式异常验证
 *
 * @author: yaozhendong
 * @create: 2019-10-29 10:54
 **/

public class ValidationUtil {
    private ValidationUtil() {

    }

    private static final String OBJECT_IS_NULL_EXCEPTION = "[ConstraintViolationException]Object is null";

    public static <T> void validateEntity(T obj) {
        validateEntity(obj, null);
    }

    /**
     * ValidationUtil.validateEntity(null,"ListLogForm is null");
     *
     * @param obj
     * @param nullExceptionMessage
     * @param <T>
     */
    public static <T> void validateEntity(T obj, String nullExceptionMessage) {
        throwNullException(obj, nullExceptionMessage);
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set.isEmpty()) {
            return;
        }
        throw new ConstraintViolationException(set);
    }

    public static <T> void validateProperty(T obj, String propertyName) {
        validateProperty(obj, propertyName);
    }

    public static <T> void validateProperty(T obj, String propertyName, String nullExceptionMessage) {
        throwNullException(obj, nullExceptionMessage);
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (set.isEmpty()) {
            return;
        }
        throw new ConstraintViolationException(set);
    }

    /**
     * 调用JSR303的validate方法, 验证失败时抛出ConstraintViolationException, 而不是返回constraintViolations.
     */
    public static void validateWithException(Object object, Class<?>... groups) {
        Validator validator = getValidator();
        Set constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    private static Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    private static <T> void throwNullException(T obj, String nullExceptionMessage) {
        if (obj == null) {
            throw new ConstraintViolationException(nullExceptionMessage == null ? OBJECT_IS_NULL_EXCEPTION : nullExceptionMessage, null);
        }
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>中为List<message>.
     */
    public static List<String> extractMessage(ConstraintViolationException e) {
        return extractMessage(e.getConstraintViolations());
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<message>
     */
    public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
        List<String> errorMessages = new ArrayList<>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为Map<property, message>.
     */
    public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e) {
        return extractPropertyAndMessage(e.getConstraintViolations());
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为Map<property, message>.
     */
    public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
        Map<String, String> errorMessages = new HashMap<>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath message>.
     */
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolations>为List<propertyPath message>.
     */
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {
        return extractPropertyAndMessageAsList(constraintViolations, " ");
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<propertyPath + separator + message>.
     */
    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<propertyPath + separator + message>.
     */
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
                                                               String separator) {
        List<String> errorMessages = new ArrayList<>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
        }
        return errorMessages;
    }

}
