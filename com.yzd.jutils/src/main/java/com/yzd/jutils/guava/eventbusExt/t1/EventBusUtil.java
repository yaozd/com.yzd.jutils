package com.yzd.jutils.guava.eventbusExt.t1;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author fengjiale
 * @create 2019-09-04 13:55
 * @desc 事件总线工具类
 **/
public class EventBusUtil {
    private static EventBus eventBus;
    private static AsyncEventBus asyncEventBus;
    private static Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    };
    private static Executor newExecutor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
    //双重锁单例模式
    private static AsyncEventBus getAsynEventBus(){
        if(asyncEventBus==null){
            synchronized (AsyncEventBus.class){
                if(asyncEventBus==null){
                    asyncEventBus = new AsyncEventBus(newExecutor);
                }
            }
        }
        return asyncEventBus;
    }
    //双重锁单例模式
    private static EventBus getEventBus(){
        if(eventBus==null){
            synchronized (EventBus.class){
                if(eventBus==null){
                    eventBus = new EventBus();
                }
            }
        }
        return eventBus;
    }
    public static void post(Object event){
        getEventBus().post(event);
    }
    //异步方式发送事件
    public static void asyncPost(Object event){
        getAsynEventBus().post(event);
    }
    public static void register(Object object){
        getEventBus().register(object);
        getAsynEventBus().register(object);
    }

}
