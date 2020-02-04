package com.yzd.jutils.executors;

import java.util.Random;
import java.util.concurrent.*;

//参考：
// Java多线程--异步执行框架Executor
// http://blog.csdn.net/hello_worldee/article/details/77880220
public class _TestMain {
    //Java异步调用-多线程实现
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用
        //多用于添加消息队列任务-使用消息及时消费
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池里面的线程数会动态变化，并可在线程线被移除前重用
        for (int i = 1; i <= 300; i++) {
            final int task = i;   //10个任务
            //TimeUnit.SECONDS.sleep(1);
            threadPool.execute(new Runnable() {    //接受一个Runnable实例
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程名字： " + Thread.currentThread().getName() + "  任务名为： " + task);
                }
            });
        }
        //threadPool.shutdownNow();

        //最终返回一上Futire用来获得任务的执行结果或取消任务
        //例3：（任务执行完成后并返回执行结果）
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {   //接受一上callable实例
            @Override
            public String call() throws Exception {
                return "MOBIN";
            }
        });
        String returnVal = future.get();
        executor.shutdownNow();
        System.out.println("任务的执行结果：" + returnVal);
        System.out.println("任务的执行结果End");
        t1();
    }

    //使用CompletionService实现任务
    //ExecutorCompletionService:实现了CompletionService，将执行完成的任务放到阻塞队列中，通过take或poll方法来获得执行结果
    static void t1() {
        ExecutorService executor = Executors.newFixedThreadPool(10);        //创建含10.条线程的线程池
        CompletionService completionService = new ExecutorCompletionService(executor);
        for (int i = 1; i <= 10; i++) {
            final int result = i;
            completionService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));   //让当前线程随机休眠一段时间
                    return result;
                }
            });
        }
        try {
            System.out.println(completionService.take().get());   //获取执行结果
        } catch (Exception ex) {

        }
    }
}
