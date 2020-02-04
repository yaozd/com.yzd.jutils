package com.yzd.jutils.annotationExt.paramValidExt;


import com.yzd.jutils.fastjson.FastJsonUtil;
import com.yzd.jutils.validationExt.ValidationResult;
import com.yzd.jutils.validationExt.ValidationUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/***
 * 通过对请参数加@ParamValid注解做参数格式验证
 *
 * Created by yzd on 2018/8/23 16:22.
 */

@Aspect
@Component
@Order(100)
public class ParamValidAspect {

    private static final Logger log = LoggerFactory.getLogger(ParamValidAspect.class);

    //@Before("execution(* com.yzd.web.controller..*(..)))||execution(* com.yzd.web.controllerApi..*(..)))")
    //@Before("execution(* com.yzd.web.controllerApi..*(..)))")
    public void paramValid(JoinPoint point) {
        Object[] param = point.getArgs();
        if (hasNoParam(param)) {
            return;
        }
        //
        //java如何获取方法参数名
        //https://blog.csdn.net/mhmyqn/article/details/47294485
        //使用反射获得参数列表里的注解getParameterAnnotations
        //https://blog.csdn.net/qq_33327210/article/details/78189239
        //获得了一个注解的二维数组。第一个维度对应参数列表里参数的数目，第二个维度对应参数列表里对应的注解。
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return;
        }
        int lengthParameterAnnotations = parameterAnnotations.length;
        List<Integer> indexParamList = new ArrayList<>();
        for (int i = 0; i < lengthParameterAnnotations; i++) {
            Annotation[] an1 = parameterAnnotations[i];
            for (Annotation an2 : an1) {
                System.out.println(an2.annotationType().getSimpleName());
                if (an2 instanceof ParamValid) {
                    indexParamList.add(i);
                    break;
                }
            }
        }
        for (Integer index : indexParamList) {
            Object obj = param[index];
            ValidationResult resultValidation = ValidationUtil.validateEntity(obj);
            if (resultValidation.isHasErrors()) {
                //String error=resultValidation.toString();
                //throw new ParamValidException(resultValidation.toString());
                String errorJosn = FastJsonUtil.serialize(resultValidation.getErrorMsg());
                throw new ParamValidException(errorJosn);
            }
        }
    }

    /**
     * 当前方法是否有参数
     *
     * @param param
     * @return
     */
    private boolean hasNoParam(Object[] param) {
        if (param == null || param.length == 0) {
            return true;
        }
        return false;
    }
}
