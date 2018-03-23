package com.yzd.jutils.springBoot.excludefilterExt;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@ComponentScan("com.hb.insure.pay.backend.schedule.consumer")
//放在这里，可以在单元测试的时候控制本机的调度任务不执行
@EnableScheduling
public class SimpleTestConfig {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTestConfig.class);

    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {

        logger.info("项目启动--BEGIN");
        SpringApplication app = new SpringApplication(SimpleTestConfig.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
        logger.info("项目启动--END");
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }
}
*/

