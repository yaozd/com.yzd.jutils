package com.yzd.jutils.springBoot.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zd.yao on 2017/7/6.
 */

@SpringBootApplication
@ComponentScan("com.yzd.jutils.springBoot.aop")
public class Test {
    public static void main(String[] args){
        //关闭banner
        /*
        SpringApplication app = new SpringApplication(Test.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);*/
        ApplicationContext ctx = SpringApplication.run(Test.class, args);
        Person person = ctx.getBean(Person.class);
        person.t1();
        System.out.println("在通过dubbo接口把消息添加到消息对队时，只是加快消息的实时性，并不希望添加操作产生的异常阻短消息");
    }
}
