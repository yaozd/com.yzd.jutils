package com.yzd.jutils.patterns.strategy.t1;

import java.util.HashMap;
import java.util.Map;

public enum HBCalculator implements ICalculator {
    渠道1算法(SalesChannel.渠道1.getId()){
        @Override
        public Object calculate(Map<String, Object> arg) {
            return 1;
        }
    },
    渠道2算法(SalesChannel.渠道2.getId()) {
        @Override
        public Object calculate(Map<String, Object> arg) {
            return 2;
        }
    },
    渠道3算法(SalesChannel.渠道3.getId()) {
        @Override
        public Object calculate(Map<String, Object> arg) {
            return 3;
        }
    };
    HBCalculator(Integer id){
        this.id=id;
    }
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //通过hashMap提高执行效率
    private static final Map<Integer,HBCalculator> enumMap=getEnumMap();

    private static Map<Integer,HBCalculator> getEnumMap() {
        Map<Integer,HBCalculator> map=new HashMap<>();
        for (HBCalculator c : HBCalculator.values()) {
            map.put(c.id,c);
        }
        return map;
    }
    // 普通方法
    public static HBCalculator getEnum(Integer id) {
        return enumMap.get(id);
    }

}
