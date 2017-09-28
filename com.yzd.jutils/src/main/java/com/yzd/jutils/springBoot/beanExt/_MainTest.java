package com.yzd.jutils.springBoot.beanExt;

/**
 * Created by zd.yao on 2017/9/28.
 */
public class _MainTest {
   /* private static final Logger logger = LoggerFactory.getLogger(ApplicationSMSSchedule.class);
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {
        String packageTimestamp=getTimestamp();
        packageTimestamp = addPackageTimestamp(packageTimestamp);
        logger.info("项目打包时间截--timestamp:"+packageTimestamp);
        logger.info("项目启动--BEGIN");
        ApplicationContext ctx = SpringApplication.run(ApplicationSMSSchedule.class, args);
        logger.info("项目启动--END");
        //把当前spring 上下文放到SpringContextUtil.getInstance()单例中，用于全局共享
        SpringContextUtil.getInstance().setCtx(ctx);
        IVerifyCodeServiceInf verifyCodeServiceInf=SpringContextUtil.getInstance().getBean(IVerifyCodeServiceInf.class);
        verifyCodeServiceInf.selectSelectiveForPage(1L);
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        closeLatch.await();
    }*/
}
