package com.yzd.jutils.conditionExt;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Created by zd.yao on 2017/7/4.
 */
public class boolExt {
    public static void main(String[] args){
        //不等于notEqual可以程序可读取
        if(ObjectUtils.notEqual("1","2")){
            return;
        }
        String t = null;
        ObjectUtils.defaultIfNull(t,"1");
    }
}
