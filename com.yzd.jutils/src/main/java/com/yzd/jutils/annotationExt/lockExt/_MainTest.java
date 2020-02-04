package com.yzd.jutils.annotationExt.lockExt;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yzd.jutils.annotationExt.lockExt")
public class _MainTest {
    //参考：
    //Java自定义注解实现Redis自动缓存
    //https://www.jianshu.com/p/9c2771b75488
    //使用 AOP 和注解实现方法缓存
    //http://blog.csdn.net/czp11210/article/details/51804461
    //java 反射获取方法返回值类型
    //https://www.cnblogs.com/cqc8/p/6869562.html
    //ProceedingJoinPoint获取当前方法
    //http://blog.csdn.net/meiyang1990/article/details/50562046
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(com.yzd.jutils.annotationExt.lockExt._MainTest.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
        com.yzd.jutils.annotationExt.lockExt.UserService userService = ctx.getBean(UserService.class);
        Integer val = userService.get(new IdForBaodanLock("12345678"), 1);
        System.out.println("redis cache val=" + val);
        System.out.println("empty val");
        throw new BaodanLockException("测试异常");
    }
}
