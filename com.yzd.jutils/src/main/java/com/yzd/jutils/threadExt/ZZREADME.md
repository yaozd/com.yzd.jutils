## Fork / Join是使用递归分解（也称为分治制
- [死磕 java线程系列之ForkJoinPool深入解析](https://www.cnblogs.com/tong-yuan/p/11824018.html)
- [基于ForkJoin构建一个简单易用的并发组件](https://my.oschina.net/u/566591/blog/1791994)


- [JAVA线程池ThreadPoolExecutor与阻塞队列BlockingQueue](https://blog.csdn.net/shixing_11/article/details/7109471)
```
public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler); 
--------------------- 
参数介绍：
corePoolSize 核心线程数，指保留的线程池大小（不超过maximumPoolSize值时，线程池中最多有corePoolSize 个线程工作）。 
maximumPoolSize 指的是线程池的最大大小（线程池中最大有corePoolSize 个线程可运行）。 
keepAliveTime 指的是空闲线程结束的超时时间（当一个线程不工作时，过keepAliveTime 长时间将停止该线程）。 
unit 是一个枚举，表示 keepAliveTime 的单位（有NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS，7个可选值）。 
workQueue 表示存放任务的队列（存放需要被线程池执行的线程队列）。 
handler 拒绝策略（添加任务失败后如何处理该任务）.
--------------------- 
 private ExecutorService threadPoolExecutor = new ThreadPoolExecutor(0, 10000, 5L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
```

- [Thread的run（）与start（）的区别](https://blog.csdn.net/qiumeng_1314/article/details/79466655)
    - run :当作普通方法的方式调用。程序还是要顺序执行，要等待run方法体执行完毕后，才可继续执行下面的代码
    - start:启动线程，真正实现了多线程运行。这时无需等待run方法体代码执行完毕
    
- 手动创建线程池
```
    private final ExecutorService executor = newThreadPoolExecutor();
    /**
     * 异步发送任务消息
     * ThreadFactoryBuilder来自：guava包
     * eg :ExecutorService executorService=Executors.newFixedThreadPool(1);
     * @return
     */
    private ExecutorService newThreadPoolExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        return new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
```