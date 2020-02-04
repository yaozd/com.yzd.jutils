package com.yzd.jutils.patterns.decorator.t1;

import com.yzd.jutils.patterns.decorator.t1.decoratorExt.DiscountStrategyForFullSubtract;
import com.yzd.jutils.patterns.decorator.t1.decoratorExt.DiscountStrategyForPercentage;

import java.util.HashMap;
import java.util.Map;

public enum DiscountStrategyEnumForGoods implements IDiscountDecorator {
    百分比折扣("percentage") {
        @Override
        public DiscountDecorator getDiscountDecorator(DiscountComponent discountComponent, Map<String, Object> param) {
            return new DiscountStrategyForPercentage(discountComponent, param);
        }
    },
    满减折扣("fullSubtract") {
        @Override
        public DiscountDecorator getDiscountDecorator(DiscountComponent discountComponent, Map<String, Object> param) {
            return new DiscountStrategyForFullSubtract(discountComponent, param);
        }
    };
    String typeDiscont;

    DiscountStrategyEnumForGoods(String typeDiscont) {
        this.typeDiscont = typeDiscont;
    }

    //===============================================
    //
    //通过hashMap提高执行效率
    private static final Map<String, DiscountStrategyEnumForGoods> enumMap = getEnumMap();

    private static Map<String, DiscountStrategyEnumForGoods> getEnumMap() {
        Map<String, DiscountStrategyEnumForGoods> map = new HashMap<>();
        for (DiscountStrategyEnumForGoods c : DiscountStrategyEnumForGoods.values()) {
            map.put(c.typeDiscont, c);
        }
        return map;
    }

    // 普通方法
    public static DiscountStrategyEnumForGoods getEnumMap(String typeDiscont) {
        return enumMap.get(typeDiscont);
    }
}
