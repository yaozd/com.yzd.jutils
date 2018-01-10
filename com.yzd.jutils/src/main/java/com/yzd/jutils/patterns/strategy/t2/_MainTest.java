package com.yzd.jutils.patterns.strategy.t2;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _MainTest {
    @Test
    public void t1(){
        PrintUtil.outLn("SalesChannel.getEnumMap(2).name()="+SalesChannel.getEnumMap(2).name());
        Map<String,Object> args=new HashMap<>();
        args.put("p1",5);
        Integer val= (Integer) SalesChannel.getEnumMap(2).getCalculator().calculate(args);
        PrintUtil.outLn(val);
    }
}
