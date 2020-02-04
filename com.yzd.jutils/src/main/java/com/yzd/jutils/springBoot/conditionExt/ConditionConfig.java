package com.yzd.jutils.springBoot.conditionExt;

//配置类声明

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

//bean声明
//条件限制

//声明为配置类
@Configuration
public class ConditionConfig {

    //注解为Bean
    @Bean
    //注解条件判断
    @Conditional(WindowsCondition.class)
    public IListServiceInf windowsListService() {
        return new WindowsListService();
    }

    //注解为Bean
    @Bean
    //注解条件判断
    @Conditional(LinuxCondition.class)
    public IListServiceInf linuxListService() {
        return new LinuxListService();
    }
}