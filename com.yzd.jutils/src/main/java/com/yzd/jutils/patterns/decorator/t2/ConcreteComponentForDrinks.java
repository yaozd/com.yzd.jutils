package com.yzd.jutils.patterns.decorator.t2;

/**
 * Created by zyf on 2017/3/30.
 */
public class ConcreteComponentForDrinks extends ComponentForDrinks {

    public ConcreteComponentForDrinks() {
        //设置name为可乐
        //这个name属性是从饮品类中继承来的
        name = "可乐";
    }

    /***
     * 实现父类的抽象方法
     * @return 可乐的价格
     */
    @Override
    public int price() {
        //可乐30块一瓶~
        return 30;
    }
}
