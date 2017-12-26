package com.yzd.jutils.annotationExt.redisCacheExt;

import com.google.common.collect.Maps;
import com.yzd.jutils.print.PrintUtil;
import com.yzd.jutils.springBoot.aop.Person;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

@SpringBootApplication
@ComponentScan("com.yzd.jutils.annotationExt.redisCacheExt")
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
    public static void main(String[] args){
        SpringApplication app = new SpringApplication(_MainTest.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
        UserService userService = ctx.getBean(UserService.class);
        Integer val= userService.get(1);
        System.out.println("redis cache val="+val);
        //目前缓存方法只接受一个请求参数,如果是多个参数的话，可以通过MAP做中间层进行传递
        Map<String, Object> whereMap = Maps.newHashMap();
        whereMap.put("k1",1);
        whereMap.put("k2","String");
        String returnOfWhereIsMap= userService.whereIsMap(whereMap);
        PrintUtil.outLn(returnOfWhereIsMap);
        //
        //userService.empty();
        System.out.println("empty val");
    }
}