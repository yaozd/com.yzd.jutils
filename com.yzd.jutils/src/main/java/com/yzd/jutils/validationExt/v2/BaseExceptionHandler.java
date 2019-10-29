package com.yzd.jutils.validationExt.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author yawei
 * @date 2019/7/8 下午4:23
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {


    /**
     * 服务器内部未知异常-需要特殊处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler()
    @ResponseBody
    void handlerException(Exception e) {
        log.error("系统异常:", e);
        String error = e.getMessage() == null ? e.toString() : e.getMessage();
        //return JsonResultError.build(JsonResultCodeEnum.ERROR.INNER_UNKNOW_ERROR.code, error);
    }

    /**
     * 参数格式异常验证 ConstraintViolationException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    void handlerConstraintViolationException(Exception e) {
        String error = e.getMessage() == null ? e.toString() : e.getMessage();
        //return JsonResultError.build(JsonResultCodeEnum.ERROR.INVALID_REQUEST_PARAMETERS.code, error);
    }
}
