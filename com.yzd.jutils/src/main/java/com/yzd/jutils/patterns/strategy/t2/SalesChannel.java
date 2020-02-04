package com.yzd.jutils.patterns.strategy.t2;

import java.util.HashMap;
import java.util.Map;

public enum SalesChannel {
    渠道1(1, HBCalculator.算法1),
    渠道2(2, HBCalculator.算法2),
    渠道3(3, HBCalculator.算法1);

    SalesChannel(Integer id, HBCalculator calculator) {
        this.id = id;
        this.calculator = calculator;
    }

    private Integer id;
    private HBCalculator calculator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HBCalculator getCalculator() {
        return calculator;
    }

    public void setCalculator(HBCalculator calculator) {
        this.calculator = calculator;
    }

    //
    //通过hashMap提高执行效率
    private static final Map<Integer, SalesChannel> enumMap = getEnumMap();

    private static Map<Integer, SalesChannel> getEnumMap() {
        Map<Integer, SalesChannel> map = new HashMap<>();
        for (SalesChannel c : SalesChannel.values()) {
            map.put(c.id, c);
        }
        return map;
    }

    // 普通方法
    public static SalesChannel getEnumMap(Integer id) {
        return enumMap.get(id);
    }
}
