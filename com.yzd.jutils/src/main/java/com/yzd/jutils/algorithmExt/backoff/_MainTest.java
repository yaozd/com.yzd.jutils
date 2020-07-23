package com.yzd.jutils.algorithmExt.backoff;

/**
 * [Exponential Backoff with Java 8](https://carlosbecker.com/posts/exponential-backoff-java8/)
 *
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {

        ExponentialBackOffFunction backOffFunction = new ExponentialBackOffFunction() {
            @Override
            public Object execute() {
                return null;
            }
        };
        ExponentialBackOff.execute(backOffFunction);
    }
}
