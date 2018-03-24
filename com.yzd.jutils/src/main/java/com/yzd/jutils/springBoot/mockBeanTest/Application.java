package com.yzd.jutils.springBoot.mockBeanTest;

import com.yzd.jutils.springBoot.banner.Test;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //关闭banner
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
    }
}
