package com.yzd.jutils.autowiredExt;

import org.springframework.stereotype.Service;

@Service("T2TempServiceImp")
public class T2TempServiceImp implements ITempServiceInf {
    @Override
    public void hello() {
        System.out.println("hello-T2TempServiceImp");
    }
}
