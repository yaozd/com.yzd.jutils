package com.yzd.jutils.springBoot.excludefilterExt;

public class Readme {

    //参考：
    //https://stackoverflow.com/questions/48102883/spring-boot-componentscan-excludefilters-not-excluding
    //=================================
    //放在这里，可以在单元测试的时候控制本机的调度任务不执行
    //一般情况下，单元测试是关闭EnableScheduling功能的。
    //@EnableScheduling
    //=================================
    //通过模拟bean使其不加载到spring上下文中
    //起到过滤的作用
    //@MockBean
    //private Starter myTestBean;
    //=================================
    //用于验证当前的类是否回载到spring的上下文当中-用于判断过滤是否起作用
    //@PostConstruct
    /**
     *
     @SpringBootApplication()
     //目前（2018-033-22-byArvin）没有测试成功
     @ComponentScan(excludeFilters=@Filter(type = FilterType.REGEX, pattern="com.wyn.applications.starter.Starter*"))
     public class SimpleTestConfig {

     }


     //

     @SpringBootApplication(excludeName = "com.hb.insure.pay.backend.schedule.consumer*")
     @ComponentScan(basePackages  = "com.hb.insure.pay.backend.schedule.consumer",
     excludeFilters = @ComponentScan.Filter(
     type = FilterType.ASSIGNABLE_TYPE,
     classes = _StatementJob.class))
     @ComponentScan(excludeFilters=@ComponentScan.Filter(type = FilterType.REGEX, pattern="com.hb.insure.pay.backend.schedule*"))
     @EnableScheduling

     //
     @Component
     public class Starter {
     //用于验证当前的类是否回载到spring的上下文当中
     @PostConstruct
     public void init(){
     System.out.println("initializing");
     }
     }

     //
     import org.springframework.boot.test.mock.mockito.MockBean;

     public class SimpleTest {
     //通过模拟bean使其不加载到spring上下文中
     @MockBean
     private Starter myTestBean;
     ...
     }

     @SpringBootApplication
     @ComponentScan("com.hb.insure.pay.backend.schedule.consumer")
     //放在这里，可以在单元测试的时候控制本机的调度任务不执行
     //一般情况下，单元测试是关闭EnableScheduling功能的。
     //@EnableScheduling
     public class SimpleTestConfig {
     private static final Logger logger = LoggerFactory.getLogger(SimpleTestConfig.class);

     @Bean
     public CountDownLatch closeLatch() {
     return new CountDownLatch(1);
     }

     public static void main(String[] args) throws InterruptedException {

     logger.info("项目启动--BEGIN");
     SpringApplication app = new SpringApplication(SimpleTestConfig.class);
     app.setBannerMode(Banner.Mode.OFF);
     ApplicationContext ctx = app.run(args);
     logger.info("项目启动--END");
     CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
     closeLatch.await();
     }
     }
     */
}
