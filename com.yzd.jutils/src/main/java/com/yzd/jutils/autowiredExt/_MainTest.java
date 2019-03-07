package com.yzd.jutils.autowiredExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class _MainTest implements CommandLineRunner {
    @Autowired
    TempServiceRepository tempServiceRepository;

    public static void main(String[] args) {
        //关闭banner
        SpringApplication app = new SpringApplication(_MainTest.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
