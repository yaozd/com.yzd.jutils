package com.yzd.jutils.autowiredExt;

import org.springframework.stereotype.Service;

@Service("T1TempServiceImp")
public class T1TempServiceImp implements ITempServiceInf {
    @Override
    public void hello() {
        System.out.println("hello-T1TempServiceImp");
    }
}
