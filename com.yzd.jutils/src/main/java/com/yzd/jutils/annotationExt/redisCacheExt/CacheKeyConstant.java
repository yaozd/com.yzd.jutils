package com.yzd.jutils.annotationExt.redisCacheExt;

/**
 * 缓存KEY常量
 * 通过常量可以知道哪些代码使用了当前的缓存
 */
public class CacheKeyConstant {
    /**
     * 获得用户
     */
    public static final String UserService_getUser = "UserService.getUser";
    public static final String UserService_getEmpty = "UserService.getEmpty";
    public static final String UserService_whereIsMap = "UserService.whereIsMap";
}
