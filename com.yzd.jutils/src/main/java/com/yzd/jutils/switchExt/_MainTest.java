package com.yzd.jutils.switchExt;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class _MainTest {


    //switch的应用场景

    //# 1个接口对应多个实现类
    @Test
    public void t1() {
        //参考：com.yzd.jutils.autowiredExt

        log.info("1个接口对应多个实现类");
    }

    //switch-代替if(或的条件
    @Test
    public void t2() {
        log.info("" + isFruits("西瓜"));
        log.info("" + isFruits("TEST"));
        //这里也是可以通过枚举代替
        log.info(FruitsEnum.isFruits("西瓜").toString());
        log.info(FruitsEnum.isFruits("TEST").toString());
    }

    private Boolean isFruits(String serialTypeName) {
        //这里也是可以通过枚举代替
        boolean bool = false;
        switch (serialTypeName) {
            case "苹果":
            case "梨":
            case "西瓜":
            case "香蕉":
                bool = true;
                break;
        }
        return bool;
    }
}
