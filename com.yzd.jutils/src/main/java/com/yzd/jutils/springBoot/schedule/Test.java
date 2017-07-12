package com.yzd.jutils.springBoot.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zd.yao on 2017/7/6.
 */
@SpringBootApplication
@ComponentScan("com.yzd.jutils.springBoot.schedule")
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {
        logger.info("项目启动--BEGIN");
        ApplicationContext ctx = SpringApplication.run(Test.class, args);
        logger.info("项目启动--END");
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }
}