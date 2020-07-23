package com.yzd.jutils.algorithmExt.backoff;

/**
 * @Author: yaozh
 * @Description:
 */
@FunctionalInterface
public interface ExponentialBackOffFunction<T> {
    T execute();
}