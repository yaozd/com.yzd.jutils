package com.yzd.jutils.blockingQueue;

import java.util.concurrent.*;

public class CallableAndFuture {
    //Java异步调用-多线程实现
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //最终返回一上Futire用来获得任务的执行结果或取消任务
        //例3：（任务执行完成后并返回执行结果）
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {   //接受一上callable实例
            @Override
            public String call() throws Exception {
                return "MOBIN";
            }
        });
        String returnVal=future.get();
        executor.shutdownNow();
        System.out.println("任务的执行结果："+returnVal);
        System.out.println("任务的执行结果End");
    }
}
