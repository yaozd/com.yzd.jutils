package com.yzd.jutils.patterns.decorator.t1;

public class DiscountComponentForShop extends DiscountComponent {

    public DiscountComponentForShop(){
        name="商店打折策略";
    }
    @Override
    public double calculateDiscount() {
        return 0;
    }
}
