package com.yzd.jutils.nullExt;

import com.google.common.base.MoreObjects;
import org.junit.Test;

import java.util.Optional;

/****
 * 理解、学习与使用 JAVA 中的 OPTIONAL
 https://www.cnblogs.com/zhangboyu/p/7580262.html
 */
public class Optional_Test {
    /***
     * 两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
     */
    @Test
    public void t1(){
        //为null给默认值--推荐MoreObjects方法-byArvin
        String ret=null;
        String val= MoreObjects.firstNonNull(ret, "默认值");//使用MoreObjects.firstNonNull
        //返回默认值-orElse()和orElseGet() 的不同之处
        Double d1=null;
        //返回对应的非空值。orElse() 方法仍然创建了 User 对象
        Double d2 = Optional.ofNullable(d1).orElse(0.0);
        System.out.println(d2);
        Double d11=null;
        //返回对应的非空值。orElseGet() 方法不创建 User 对象。
        Double d21 = Optional.ofNullable(d11).orElseGet(()->0.0);
        System.out.println(d21);
    }
}
