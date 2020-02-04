package com.yzd.jutils.patterns.decorator.t1.decoratorExt;

import com.yzd.jutils.patterns.decorator.t1.DiscountComponent;
import com.yzd.jutils.patterns.decorator.t1.DiscountDecorator;

import java.util.Map;

public class DiscountStrategyForPercentage extends DiscountDecorator {
    Map<String, Object> param;

    public DiscountStrategyForPercentage(DiscountComponent discountComponent, Map<String, Object> param) {
        super(discountComponent);
        this.param = param;
    }

    @Override
    public double calculateDiscount() {
        double percent = getValueDouble("percent");
        return discountComponentObj.calculateDiscount() * percent;
    }

    @Override
    public String getName() {
        return "百分比打折策略";
    }

    private double getValueDouble(String key) {
        return Double.parseDouble(param.get(key).toString());
    }
}
