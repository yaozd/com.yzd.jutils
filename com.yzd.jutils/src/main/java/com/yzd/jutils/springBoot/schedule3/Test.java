package com.yzd.jutils.springBoot.schedule3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * 计划任务建议使用同步的线程池
 *
 * 消息队列版的任务调度--版本v1
 *
 * Created by zd.yao on 2017/7/7.
 */
@SpringBootApplication
@ComponentScan("com.yzd.jutils.springBoot.schedule3")
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
