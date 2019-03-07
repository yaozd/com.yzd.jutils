package com.yzd.jutils.autowiredExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TempServiceRepository {

    @Qualifier("T1TempServiceImp")
    @Autowired
    ITempServiceInf t1TempServiceImp;
    @Qualifier("T2TempServiceImp")
    @Autowired
    ITempServiceInf t2TempServiceImp;

    public ITempServiceInf getITempServiceInf(Integer code){
        switch (code){
            case 1:return t1TempServiceImp;
            case 2:return t2TempServiceImp;
        }
        throw new IllegalArgumentException("没有找到对应ITempServiceInf实现;CODE="+code);
    }

}
