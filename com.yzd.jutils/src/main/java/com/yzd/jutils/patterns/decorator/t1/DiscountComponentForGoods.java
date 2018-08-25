package com.yzd.jutils.patterns.decorator.t1;

public class DiscountComponentForGoods extends DiscountComponent {

    private double price;

    /***
     *
     * @param price 商品价格
     */
    public DiscountComponentForGoods(double price){
        name="商品打折策略";
        this.price=price;
    }
    @Override
    public double calculateDiscount() {
        return price;
    }
}