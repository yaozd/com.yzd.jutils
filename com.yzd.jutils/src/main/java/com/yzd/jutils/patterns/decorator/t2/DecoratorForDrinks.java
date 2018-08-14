package com.yzd.jutils.patterns.decorator.t2;

/**
 * Created by zyf on 2017/3/30.
 * 装饰者模式中,所有装饰者的父类
 */
public abstract class DecoratorForDrinks extends ComponentForDrinks {

    /***
     * 声明一个饮品引用,准备接受一个饮品对象<br/>
     */
    protected ComponentForDrinks yp;

    public DecoratorForDrinks(ComponentForDrinks yp) {
        this.yp = yp;
    }

}