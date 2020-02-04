package com.yzd.jutils.springBoot.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zd.yao on 2017/7/6.
 */
@SpringBootApplication
@ComponentScan("com.yzd.jutils.springBoot.banner")
public class Test {
    public static void main(String[] args) {
        //关闭banner
        SpringApplication app = new SpringApplication(Test.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
        //ApplicationContext ctx = SpringApplication.run(Test.class, args);
        System.out.println("关闭banner");
    }
}