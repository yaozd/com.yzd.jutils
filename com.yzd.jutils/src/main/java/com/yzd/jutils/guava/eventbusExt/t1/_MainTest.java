package com.yzd.jutils.guava.eventbusExt.t1;

import java.time.Instant;

/**
 * [EventBus和AsyncEventBus使用区别](https://blog.csdn.net/qq_38345296/article/details/100539989)
 *
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {
        EventListener1 listener1 = new EventListener1();
        EventListener2 listener2 = new EventListener2();
        CustomEvent customEvent = new CustomEvent(23);
        EventBusUtil.register(listener1);
        EventBusUtil.register(listener2);
        //同步模式
        EventBusUtil.post(customEvent);
        //异步模式
        for (int i = 0; i < 1000000; i++) {
            EventBusUtil.asyncPost(customEvent);
        }
        //EventBusUtil.asyncPost(customEvent);
//        try {
//            Thread.sleep(10*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Instant.now() + ",主线程执行完毕：" + Thread.currentThread().getName());
    }
}
