package com.yzd.jutils.guava.eventbusExt.t2;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * [google Guava之EventBus](https://blog.csdn.net/wangdong5678999/article/details/80561198) -推荐参考byArvin
 * 不同类型参数的Subscribe
 * eventBus会根据Listener的参数类型的不同，分别向不同的Subscribe发送不同的消息。
 *
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new MultipleEventListeners());
        System.out.println("post the string event.");
        eventBus.post("I am String event");
        System.out.println("post the int event.");
        eventBus.post(1000);
    }

    public static class MultipleEventListeners {
        private final static Logger LOGGER = LoggerFactory.getLogger(MultipleEventListeners.class);

        @Subscribe
        public void task1(final String event) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Received event [{}] and will take a action by ==task1==", event);
            }
        }

        @Subscribe
        public void task2(final String event) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Received event [{}] and will take a action by ==task2==", event);
            }
        }

        @Subscribe
        public void intTask(final Integer event) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Received event [{}] and will take a action by ==intTask==", event);
            }
        }
    }
}