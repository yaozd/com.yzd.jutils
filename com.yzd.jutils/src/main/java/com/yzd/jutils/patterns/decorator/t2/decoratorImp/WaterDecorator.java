package com.yzd.jutils.patterns.decorator.t2.decoratorImp;


import com.yzd.jutils.patterns.decorator.t2.ComponentForDrinks;
import com.yzd.jutils.patterns.decorator.t2.DecoratorForDrinks;

/**
 * Created by zyf on 2017/3/30.
 */
public class WaterDecorator extends DecoratorForDrinks {


    public WaterDecorator(ComponentForDrinks yp) {
        super(yp);
    }

    public void addWater() {
        System.out.println("饮料兑水....尴尬不老铁...");
    }

    /***
     * 那么兑水后的价格应该是多少呢?<br/>
     * 应该是兑水的价格加饮品的价格
     * @return 兑水2块
     */
    @Override
    public int price() {
        return 2 + yp.price();
    }

    /***
     * 再复写一个名字的方法<br/>
     * 现在已经不是单纯的饮品了
     * @return
     */
    @Override
    public String getName() {
        addWater();
        return "兑水了的" + yp.getName();
    }
}