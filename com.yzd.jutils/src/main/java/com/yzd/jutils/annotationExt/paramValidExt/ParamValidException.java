package com.yzd.jutils.annotationExt.paramValidExt;

/***
 *
 * 只用于参数规则验证的异常信息
 * Created by yzd on 2018/8/24 8:28.
 */

public class ParamValidException extends RuntimeException {
    /**
     * 构造一个基本异常.
     *
     * @param message 信息描述
     */
    public ParamValidException(String message) {
        super(message);
    }
}
