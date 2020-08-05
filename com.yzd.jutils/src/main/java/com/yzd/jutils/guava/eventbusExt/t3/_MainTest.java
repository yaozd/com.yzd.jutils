package com.yzd.jutils.guava.eventbusExt.t3;

import com.google.common.eventbus.EventBus;

/**
 * 继承关系的event
 *
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());
        eventBus.post(new Apple("apple"));

        System.out.println("---------------------");
        eventBus.post(new Fruit("Fruit"));
    }
}
