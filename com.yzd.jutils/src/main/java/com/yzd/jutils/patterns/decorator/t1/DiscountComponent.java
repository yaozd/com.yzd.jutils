package com.yzd.jutils.patterns.decorator.t1;

public abstract class DiscountComponent {
    String name;

    public abstract double calculateDiscount();

    /***
     * 得到打折策略的名字
     * @return 名字
     */
    public String getName() {
        return name;
    }
}
