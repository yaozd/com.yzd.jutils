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
    // Java利用ShutDownHook关闭系统资源
    //http://blog.csdn.net/jaune161/article/details/46422881
    ShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在以下几种场景被调用：
    1）程序正常退出
    2）使用System.exit()
    3）终端使用Ctrl+C触发的中断
    4）系统关闭
    5）使用Kill pid命令干掉进程
    ===
    window 下可以通过Ctrl+C触发关闭
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
     //=================
    public static void main(String[] args) throws InterruptedException {
    logger.info("项目启动--BEGIN");
    SpringApplication app = new SpringApplication(Application.class);
    app.addListeners(new EventListener());
    ApplicationContext ctx =app.run(args);
    logger.info("项目启动--END");
    CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
    closeLatch.await();
    }
     */
}
