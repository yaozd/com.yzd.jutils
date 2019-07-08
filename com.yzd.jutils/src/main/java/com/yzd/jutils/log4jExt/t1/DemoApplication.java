package com.yzd.jutils.log4jExt.t1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    static {
        //这样保证程序启动时，最先运行，设置本地ip。
        //这样保证程序启动时，最先运行，设置本地ip。
        SystemExtend.initProperty();
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println(System.getProperty("local-ip"));
    }

}
