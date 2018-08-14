package com.yzd.jutils.patterns.decorator.t1;

import com.yzd.jutils.patterns.decorator.t1.decoratorExt.DiscountStrategyForFullSubtract;
import com.yzd.jutils.patterns.decorator.t1.decoratorExt.DiscountStrategyForPercentage;

import java.util.HashMap;
import java.util.Map;

public class _MainTest {
    public static void main(String[] args) {
        //通过枚举策略模式+装饰模式 实现商品价格折扣计算（当前策略只是针对商品的折扣策略）
        //折扣大类型：
        // 1.商品折扣-按百分比
        // 2.订单折扣-满减
        // 3.商户活动
        // 4.商家活动
        // 5.平台活动
        // 6.等等其他折扣
        t1();
        t2();
    }
    private static void t1() {
        //通用参数有两种：
        //1.Map<String,Object> param （缺点参数值验证困难）
        // 2.Json格式的字符串，然后再通过注解来验证
        //通过枚举配置策略
        DiscountComponent discountComponent=new DiscountComponentForGoods(100);
        //
        Map<String,Object> paramForPercentage=new HashMap<>();
        paramForPercentage.put("percent",0.5);
        discountComponent=DiscountStrategyEnumForGoods.getEnumMap("percentage").getDiscountDecorator(discountComponent,paramForPercentage);
        //
        Map<String,Object> paramForFullSubtract=new HashMap<>();
        paramForFullSubtract.put("mixPrice",5);
        paramForFullSubtract.put("subtractPrice",1);
        discountComponent=DiscountStrategyEnumForGoods.getEnumMap("fullSubtract").getDiscountDecorator(discountComponent,paramForFullSubtract);
        //
        double finalPrice=discountComponent.calculateDiscount();
        System.out.println(finalPrice);
    }
    private static void t2() {
        //直接使用装饰模式添加策略
        DiscountComponent discountComponent=new DiscountComponentForGoods(100);
        //
        Map<String,Object> paramForPercentage=new HashMap<>();
        paramForPercentage.put("percent",0.5);
        discountComponent=new DiscountStrategyForPercentage(discountComponent,paramForPercentage);
        //
        Map<String,Object> paramForFullSubtract=new HashMap<>();
        paramForFullSubtract.put("mixPrice",5);
        paramForFullSubtract.put("subtractPrice",1);
        discountComponent=new DiscountStrategyForFullSubtract(discountComponent,paramForFullSubtract);
        //
        double finalPrice=discountComponent.calculateDiscount();
        System.out.println(finalPrice);
    }
}
