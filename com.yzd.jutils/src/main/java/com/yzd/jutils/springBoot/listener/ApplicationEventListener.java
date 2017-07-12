package com.yzd.jutils.springBoot.listener;

/**
 * Created by zd.yao on 2017/6/22.
 */
public class ApplicationEventListener {
    /***
     * //实现ApplicationListener接口：
     public class ApplicationEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    // 在这里可以监听到Spring Boot的生命周期
    eventHandler(event);
    }
    private void eventHandler(ApplicationEvent event) {
    // 在这里可以监听到Spring Boot的生命周期
    if (event instanceof ApplicationEnvironmentPreparedEvent) {
    // 初始化环境变量
    System.out.println("初始化环境变量");
    return;
    }
    if (event instanceof ApplicationPreparedEvent) {
    // 初始化完成
    System.out.println("初始化完成");
    return;
    }
    if (event instanceof ContextRefreshedEvent) {
    // 应用刷新
    System.out.println("应用刷新");
    return;
    }
    if (event instanceof ApplicationReadyEvent) {
    // 应用已启动完成
    System.out.println("应用已启动完成");
    return;
    }
    if (event instanceof ContextStartedEvent) {
    // 应用启动，需要在代码动态添加监听器才可捕获
    System.out.println("应用启动，需要在代码动态添加监听器才可捕获");
    return;
    }
    if (event instanceof ContextStoppedEvent) {
    // 应用停止
    System.out.println("应用停止");
    return;
    }
    if (event instanceof ContextClosedEvent) {
    // 应用关闭
    // kill PID 不要使用kill -9 PID
    System.out.println("应用关闭");
    return;
    }
    }
    }
     */
    /**
     * @SpringBootApplication
    public class ListenDemoApplication {

    public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ListenDemoApplication.class);
    app.addListeners(new ApplicationEventListener());
    app.run(args);
    }
    }
     */
}
