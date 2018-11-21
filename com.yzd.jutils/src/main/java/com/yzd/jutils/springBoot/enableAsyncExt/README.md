### [spring-boot @Async 的使用、自定义Executor的配置方法](https://blog.csdn.net/ClementAD/article/details/53607311)

```
前面是最简单的使用方法。如果想使用自定义的Executor，可以按照如下几步来：

1、新建一个Executor配置类，顺便把@EnableAsync注解搬到这里来：

@Configuration
@EnableAsync
public class ExecutorConfig {
 
	/** Set the ThreadPoolExecutor's core pool size. */
	private int corePoolSize = 10;
	/** Set the ThreadPoolExecutor's maximum pool size. */
	private int maxPoolSize = 200;
	/** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
	private int queueCapacity = 10;
 
	@Bean
	public Executor mySimpleAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MySimpleExecutor-");
		executor.initialize();
		return executor;
	}
	
	@Bean
	public Executor myAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MyExecutor-");
 
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}
这里定义了两个不同的Executor，第二个重新设置了pool已经达到max size时候的处理方法；同时指定了线程名字的前缀。

2、自定义Executor的使用：

/**
 * Asynchronous Tasks
 * @author Xu
 *
 */
@Component
public class AsyncTask {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async("mySimpleAsync")
	public Future<String> doTask1() throws InterruptedException{
		logger.info("Task1 started.");
		long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        
        logger.info("Task1 finished, time elapsed: {} ms.", end-start);
        
        return new AsyncResult<>("Task1 accomplished!");
	}
	
	@Async("myAsync")
	public Future<String> doTask2() throws InterruptedException{
		logger.info("Task2 started.");
		long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        
        logger.info("Task2 finished, time elapsed: {} ms.", end-start);
        
        return new AsyncResult<>("Task2 accomplished!");
	}
}
就是把上面自定义Executor的类名，放进@Async注解中。


3、测试（测试用例不变）结果：

2016-12-13 10:57:11,998:INFO MySimpleExecutor-1 (AsyncTask.java:22) - Task1 started.
2016-12-13 10:57:12,001:INFO MyExecutor-1 (AsyncTask.java:34) - Task2 started.
2016-12-13 10:57:15,007:INFO MyExecutor-1 (AsyncTask.java:39) - Task2 finished, time elapsed: 3000 ms.
2016-12-13 10:57:16,999:INFO MySimpleExecutor-1 (AsyncTask.java:27) - Task1 finished, time elapsed: 5001 ms.
2016-12-13 10:57:17,994:INFO main (TaskTests.java:23) - Task1 result: Task1 accomplished!
2016-12-13 10:57:17,994:INFO main (TaskTests.java:24) - Task2 result: Task2 accomplished!
2016-12-13 10:57:17,994:INFO main (TaskTests.java:30) - All tasks finished.
2016-12-13 10:57:18,064 Thread-3 WARN Unable to register Log4j shutdown hook because JVM is shutting down. Using SimpleLogger

可见，线程名字的前缀变了，两个task使用了不同的线程池了。
--------------------- 
作者：Clement-Xu 
来源：CSDN 
原文：https://blog.csdn.net/ClementAD/article/details/53607311 
版权声明：本文为博主原创文章，转载请附上博文链接！
```