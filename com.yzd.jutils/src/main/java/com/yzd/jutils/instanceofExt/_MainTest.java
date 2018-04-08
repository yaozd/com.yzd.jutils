package com.yzd.jutils.instanceofExt;

public class _MainTest {
    //偏执却管用的10条Java编程技巧
    //http://www.importnew.com/16805.html
    /**
     * // Bad
     <T> void bad(T value) {
     bad(Collections.singletonList(value));
     }

     <T> void bad(List<T> values) {
     ...
     }

     // Good
     final <T> void good(final T value) {
     if (value instanceof List)
     good((List<?>) value);
     else
     good(Collections.singletonList(value));
     }

     final <T> void good(final List<T> values) {
     ...
     }
     */
}
