package com.yzd.jutils.patterns.decorator.t2.decoratorImp;


import com.yzd.jutils.patterns.decorator.t2.ComponentForDrinks;
import com.yzd.jutils.patterns.decorator.t2.DecoratorForDrinks;

/**
 * Created by zyf on 2017/3/30.
 */
public class VinegarDecorator extends DecoratorForDrinks {


    public VinegarDecorator(ComponentForDrinks yp) {
        super(yp);
    }

    public void addVinegar(){
        System.out.println("还要加醋,加完了");
    }

    /***
     * 那么加醋后的价格应该是多少呢?<br/>
     * 应该是加粗的价格加饮品的价格
     * @return 加醋五块
     */
    @Override
    public int price() {
        return 5 + yp.price();
    }

    /***
     * 再复写一个名字的方法<br/>
     * 现在已经不是单纯的饮品了
     * @return
     */
    @Override
    public String getName() {
        //在这里加个醋
        addVinegar();
        return "加醋的" + yp.getName();
    }
}