package com.yzd.jutils.patterns.decorator.t2;

import com.yzd.jutils.patterns.decorator.t2.decoratorImp.VinegarDecorator;
import com.yzd.jutils.patterns.decorator.t2.decoratorImp.WaterDecorator;

public class _MainTest {
    public static void main(String[] args) {
        //可以看到,我们操作的引用一直是这个yp
        //但是这个引用指向的对象已经换了好几次了
        //这就是为什么装饰类也要是饮品类的子类,因为只有这样,装饰类与被装饰类才能被当做同一个类型使用(通过接口或继承实现)
        ComponentForDrinks yp = new ConcreteComponentForDrinks();
        yp = new WaterDecorator(yp);
        yp = new VinegarDecorator(yp);
        //  上面与下面这一行是一样的,是不是和IO流很像?
        //  yp = new 加醋Decorator(new 兑水Decorator(new 可乐Component()));
        System.out.println("饮品名:" + yp.getName() + "---价格:" + yp.price());
    }
}
