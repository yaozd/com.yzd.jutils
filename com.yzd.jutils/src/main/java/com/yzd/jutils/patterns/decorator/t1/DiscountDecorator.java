package com.yzd.jutils.patterns.decorator.t1;

public abstract class DiscountDecorator extends DiscountComponent {
    /***
     * 声明一个打折引用,准备接受一个打折对象
     */
    protected DiscountComponent discountComponentObj;

    public DiscountDecorator(DiscountComponent discountComponent) {
        this.discountComponentObj = discountComponent;
    }
}
