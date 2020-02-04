package com.yzd.jutils.person;

import java.io.Serializable;

/**
 * Created by zd.yao on 2017/4/28.
 */
public class Person implements Serializable {

    public Person() {

    }

    public Person(int id, String address, String name, int age) {
        this.address = address;
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private int id;
    private String name, address;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
