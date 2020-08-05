package com.yzd.jutils.guava.eventbusExt.t1;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import java.time.Instant;

/**
 * @Author: yaozh
 * @Description:
 */

public class EventListener2 {
    @Subscribe
    @AllowConcurrentEvents
    public void test(CustomEvent event){
        System.out.println(Instant.now() +",监听者2,收到事件："+event.getAge()+"，线程号为："+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
