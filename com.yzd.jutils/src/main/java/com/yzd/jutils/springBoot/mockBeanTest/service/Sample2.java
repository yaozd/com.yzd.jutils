package com.yzd.jutils.springBoot.mockBeanTest.service;

import org.springframework.stereotype.Component;

@Component
public class Sample2 {

    public String gg(boolean flag) {
        if (flag) {
            throw new IllegalStateException("gg(boolean flag=true)");
        }
        return "NewSimpleTest";
    }
}