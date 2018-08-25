package com.yzd.jutils.patterns.decorator.t1.decoratorExt;

import com.yzd.jutils.patterns.decorator.t1.DiscountComponent;
import com.yzd.jutils.patterns.decorator.t1.DiscountDecorator;

import java.util.HashMap;
import java.util.Map;

public class DiscountStrategyForFullSubtract  extends DiscountDecorator {

    Map<String,Object> param;
    public DiscountStrategyForFullSubtract(DiscountComponent discountComponent,Map<String,Object> param) {
        super(discountComponent);
        this.param=param;
    }


    @Override
    public double calculateDiscount() {
        double price= discountComponentObj.calculateDiscount();
        double mixPrice= getValueDouble("mixPrice");
        double subtractPrice=getValueDouble("subtractPrice");
        return price>=mixPrice?price-subtractPrice:price;
    }

    private double getValueDouble(String key) {
        return Double.parseDouble(param.get(key).toString());
    }

    @Override
    public String getName(){
        return "满减打折策略";
    }
}
