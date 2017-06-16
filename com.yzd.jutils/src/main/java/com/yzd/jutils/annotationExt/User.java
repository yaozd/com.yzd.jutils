package com.yzd.jutils.annotationExt;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class User {
    private String	name;
    private String	age;
    public String getName() {
        return name;
    }
    @Value(value = "liang")
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    @Value(value = "23")
    public void setAge(String age) {
        this.age = age;
    }
}