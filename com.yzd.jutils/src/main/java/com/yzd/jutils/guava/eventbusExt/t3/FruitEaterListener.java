package com.yzd.jutils.guava.eventbusExt.t3;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: yaozh
 * @Description:
 */
public class FruitEaterListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(FruitEaterListener.class);

    @Subscribe
    public void eat(Fruit event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Fruit eat[{}]. ", event);
        }
    }
    @Subscribe
    public void eat(Apple event){
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("Apple eat[{}]. ", event);
        }
    }
}
