package com.yzd.jutils.annotationExt;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 使用lombok的方式不能通过自定义注解进行赋值
 * Created by zd.yao on 2017/6/15.
 */
@Data
@NoArgsConstructor
public class Person {
    @Value("yzd")
    private String	name;
    private String	age;
}
