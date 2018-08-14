package com.yzd.jutils.patterns.decorator.t1;

import java.util.Map;

public interface IDiscountDecorator {
    DiscountDecorator getDiscountDecorator(DiscountComponent discountComponent,Map<String,Object> param);
}
