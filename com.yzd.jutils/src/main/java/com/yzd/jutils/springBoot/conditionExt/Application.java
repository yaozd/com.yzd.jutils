package com.yzd.jutils.springBoot.conditionExt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(com.yzd.jutils.springBoot.schedule3.Test.class);
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {
        logger.info("项目启动--BEGIN");
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        logger.info("项目启动--END");
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        IListServiceInf listService = ctx.getBean(IListServiceInf.class);
        System.out.println(ctx.getEnvironment().getProperty("os.name") + "系统的查看目录的命令是： " + listService.showListCmd()
        );
        closeLatch.await();
    }
}
