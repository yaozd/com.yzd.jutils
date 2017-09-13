package com.yzd.jutils.springBoot.application;

/**
 * Created by zd.yao on 2017/7/6.
 */
public class Application {
/*    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.context.annotation.ImportResource;
    import org.springframework.scheduling.annotation.EnableScheduling;

    *//**
     * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
     * 解决：
     * The dependencies of some of the beans in the application context form a cycle
     * Created by zd.yao on 2017/6/26.
     *//*
    @SpringBootApplication
    @ComponentScan("com.yzd.dubbo.monitor.web,com.yzd.dubbo.monitor.service")
    @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
    @ImportResource("classpath:com.yzd.dubbo.monitor.provider.xml")
    @EnableScheduling
    //如果发现missing.properties不存在，则抛出异常，也可以使用ignoreResourceNotFound=true去忽略
    //@PropertySource(value = "classpath:server.properties",ignoreResourceNotFound = true)
    @PropertySource(value = "classpath:server.properties")
    //@Autowired Environment可以读取server.properties中的数据
    //private Environment env;
    //String val= env.getProperty("project.isTest");
    public class ApplicationMonitorWeb {
        *//**
         * Used when run as JAR
         *//*
        public static void main(String[] args) {
            SpringApplication.run(ApplicationMonitorWeb.class, args);
        }
    }*/
}
