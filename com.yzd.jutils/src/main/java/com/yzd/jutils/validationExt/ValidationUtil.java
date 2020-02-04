package com.yzd.jutils.validationExt;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ValidationUtil {
    public static <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        if (obj == null) {
            result.setHasErrors(true);
            return result;
        }
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (!set.isEmpty()) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        if (obj == null) {
            result.setHasErrors(true);
            return result;
        }
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (!set.isEmpty()) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    /**
     * 验证是否是日期格式 yyyy-MM-dd
     */
    public static boolean isDateString(String input) {
        return Pattern.matches("^[1-9]\\d{3}-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2]\\d)|(3[0-1]))$", input);
    }

    /**
     * 验证是否是手机号
     */
    public static boolean isMobile(String input) {
        return Pattern.matches("^1\\d{10}$", input);
    }

    /**
     * 身份证号校验
     */
    public static boolean isIdCard(String input) {
        if (StringUtils.isBlank(input)) {
            return false;
        }
        String value = input.toUpperCase().trim();
        String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\\d)|(3[0-1]))\\d{3}[xX\\d]$";

        if (!Pattern.matches(regex, input)) {
            return false;
        }
        String cityCodeJoins = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";
        if (cityCodeJoins.indexOf(value.substring(0, 2)) == -1) {
            return false;
        } else {
            //18位身份证需要验证最后一位校验位
            // ∑(ai×Wi)(mod 11)
            //加权因子
            int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            //校验位
            char[] parity = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
            int sum = 0;
            int ai = 0;
            int wi = 0;
            for (int i = 0; i < 17; i++) {
                ai = Integer.parseInt(value.substring(i, i + 1));
                wi = factor[i];
                sum += ai * wi;
            }
            char last = parity[sum % 11];
            if (last != value.charAt(17)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中文姓名校验
     */
    public static boolean isRealName(String input) {
        return Pattern.matches("^[\\u4e00-\\u9fa5]+(·[\\u4e00-\\u9fa5]+)*$", input);
    }

    /**
     * 邮箱校验
     */
    public static boolean isEmail(String input) {
        return Pattern.matches("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,5}$", input);
    }
}
