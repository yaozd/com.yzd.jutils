package com.yzd.jutils.patterns.strategy.t1;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _MainTest {
    @Test
    public void t1(){
        PrintUtil.outLn("HBCalculator.getEnum(1)="+HBCalculator.getEnum(1).name());
        Map<String,Object> args=new HashMap<>();
        args.put("p1",5);
        Integer val= (Integer) HBCalculator.getEnum(2).calculate(args);
        PrintUtil.outLn(val);
    }
}
