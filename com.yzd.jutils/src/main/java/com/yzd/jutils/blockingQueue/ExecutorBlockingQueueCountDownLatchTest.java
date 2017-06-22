package com.yzd.jutils.blockingQueue;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yzd.jutils.print.PrintUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zd.yao on 2017/6/21.
 */
public class ExecutorBlockingQueueCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 1000; i++) {
            Test02();
            PrintUtil.outLn("=============FFFFFFF=========" + i);
        }
        //Test02();
        //-------------------------------
/*        for(int i=0;i<100;i++){
            Test01();
        }
        Test01();*/
    }

    /**
     * 版本2：callable
     * Java多线程之~~~Callable接口获得返回值
     * http://www.2cto.com/kf/201409/337135.html
     */
    private static void Test02() throws InterruptedException, ExecutionException {
        //生成测试数据
        List<Integer> userIdList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            userIdList.add(1);
        userIdList.add(2);
        userIdList.add(3);
        userIdList.add(4);
        userIdList.add(5);
        userIdList.add(6);
        userIdList.add(7);
        //list to multimap 每2个做为一个任务
        Multimap<Integer, Integer> multimap = ArrayListMultimap.create();
        Integer userIdListSize = userIdList.size();
        for (Integer i = 0; i < userIdListSize; i++) {
            multimap.put(i / 2, userIdList.get(i));
        }
        //返回结果
        List<Future<Integer>> result = Lists.newArrayList();
        //
        int multimapMapSize = multimap.asMap().size();
        CountDownLatch latch = new CountDownLatch(multimapMapSize);
        final Exception[] threadException = {new Exception()};
        //
        ExecutorService executor = Executors.newFixedThreadPool(13);
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(30, 30, 1,TimeUnit.SECONDS, new LinkedBlockingQueue());
        for (Collection collection : multimap.asMap().values()) {
            Task task = new Task(Lists.newArrayList(collection), latch);
            Future<Integer> future = executor.submit(task);
            result.add(future);
        }
        latch.await();
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        if (result.size() != multimapMapSize) {
            throw new IllegalStateException("result.size()不等于multimapMapSize的大小，任务执行失败");
        }
        PrintUtil.outLn("multimap.asMap().size()---" + multimapMapSize);
        PrintUtil.outLn("result.size()" + result.size());
        PrintUtil.outLn(result.toString());
        List<Integer> finalResult = Lists.newArrayList();
        try {
            for (Future<Integer> future : result) {
                Integer value = future.get();
                if (value == null) {
                    throw new IllegalStateException("future.get()值等于null");
                }
                finalResult.add(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintUtil.outLn("multimapMapSize=" + multimapMapSize);
        PrintUtil.outLn("finalResult.size()=" + finalResult.size());
        if (finalResult.size() != multimapMapSize) {
            throw new IllegalStateException("finalResult.size()不等于multimapMapSize的大小，任务执行失败");
        }
        PrintUtil.outLn(finalResult.toString());

    }

    /**
     * 测试-mapreduce原理
     * todo 此版有问题会出result=[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, null, 2, 2, 2] result=null的现象
     *
     * @throws InterruptedException
     */
    private static void Test01() throws InterruptedException {
        //生成测试数据
        List<Integer> userIdList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            userIdList.add(1);
        userIdList.add(2);
        userIdList.add(3);
        userIdList.add(4);
        userIdList.add(5);
        userIdList.add(6);
        userIdList.add(7);
        //list to multimap 每2个做为一个任务
        Multimap<Integer, Integer> multimap = ArrayListMultimap.create();
        Integer userIdListSize = userIdList.size();
        for (Integer i = 0; i < userIdListSize; i++) {
            multimap.put(i / 2, userIdList.get(i));
        }
        //返回结果
        List<Integer> result = Lists.newArrayList();
        //
        int multimapMapSize = multimap.asMap().size();
        CountDownLatch latch = new CountDownLatch(multimapMapSize);
        final Exception[] threadException = {new Exception()};
        //
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(8);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 8, 1, TimeUnit.MINUTES, queue, new ThreadPoolExecutor.CallerRunsPolicy());
        for (Collection collection : multimap.asMap().values()) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        //如果把tempList放在Runnable的方面就有可能出现tempList为null的现象-出现报错问题
                        List<Integer> tempList = Lists.newArrayList(collection);
                        //region 具体实现方法
                        TimeUnit.SECONDS.sleep(1);
                        PrintUtil.outLn("tempList.size()===" + tempList.size());
                        PrintUtil.outLn("///////////////");
                        //int a2=1/0; //为测试产生一个异常
                        //TimeUnit.SECONDS.sleep(5);
                        TimeUnit.SECONDS.sleep(1);
                        PrintUtil.outLn("/////1221//////////");
                        result.add(tempList.size());
                        //endregion
                    } catch (Exception e) {
                        threadException[0] = e;
                        executor.shutdownNow();//中断所有请求
                        while (latch.getCount() > 0) {
                            latch.countDown();
                        }
                    }
                    latch.countDown();
                }
            };
            executor.execute(run);
        }
        //如果设置了整体任务的执行超时时间后可以加此判断
        //latch.await(5,TimeUnit.SECONDS);
        latch.await();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        executor.shutdown();
        if (threadException[0].getMessage() != null) {
            throw new IllegalStateException(threadException[0]);
        }
        if (result.size() != multimapMapSize) {
            throw new IllegalStateException("result.size()不等于multimapMapSize的大小，任务执行失败");
        }
        //此时你会发现任务完成的数量与multimapMapSize的大小不同，这可能是由于ThreadPoolExecutor内部的更新机制有关
        //但从result.size()的大小必须等于multimapMapSize的大小
        PrintUtil.outLn("executor.getCompletedTaskCount()-----" + executor.getCompletedTaskCount());
        PrintUtil.outLn("multimap.asMap().size()---" + multimapMapSize);
        PrintUtil.outLn("result.size()" + result.size());
        PrintUtil.outLn(result.toString());
        /*
        //如果设置了整体任务的执行超时时间后可以加此判断
        latch.await(5,TimeUnit.SECONDS);
        if(latch.getCount()>0){
            throw new IllegalStateException("任务执行超时");
        }*/
    }
}

class Task implements Callable<Integer> {
    List<Integer> tempList;
    CountDownLatch latch;

    public Task(List<Integer> list, CountDownLatch latch) {
        super();
        this.tempList = list;
        this.latch = latch;
    }

    @Override
    public Integer call() {
        try {

            PrintUtil.outLn(1);
            PrintUtil.outLn(latch.getCount());
            if (latch.getCount() < 30) {
                //int a2 = 1 / 0;//为测试产生一个异常
            }
            TimeUnit.MICROSECONDS.sleep(100);
            return tempList.size();
        } catch (Exception e) {
            while (latch.getCount() > 0) {
                latch.countDown();
            }
            throw new IllegalStateException(e);
        } finally {
            latch.countDown();
        }
    }

}
