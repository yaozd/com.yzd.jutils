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
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<100;i++){
            Test01();
        }
        Test01();
    }

    /***
     * 测试-mapreduce原理
     * todo 此版有问题会出result=[2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, null, 2, 2, 2] result=null的现象
     * @throws InterruptedException
     */
    private static void Test01() throws InterruptedException {
        //生成测试数据
        List<Integer> userIdList=new ArrayList<>();
        for(int i=0;i<100;i++)
        userIdList.add(1);
        userIdList.add(2);
        userIdList.add(3);
        userIdList.add(4);
        userIdList.add(5);
        userIdList.add(6);
        userIdList.add(7);
        //list to multimap 每2个做为一个任务
        Multimap<Integer,Integer> multimap= ArrayListMultimap.create();
        Integer userIdListSize=userIdList.size();
        for(Integer i=0;i<userIdListSize;i++){
            multimap.put(i / 2, userIdList.get(i)) ;
        }
        //返回结果
        List<Integer>result= Lists.newArrayList();
        //
        int multimapMapSize=multimap.asMap().size();
        CountDownLatch latch=new CountDownLatch(multimapMapSize);
        final Exception[] threadException = {new Exception()};
        //
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(8);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 8, 1, TimeUnit.MINUTES, queue, new ThreadPoolExecutor.CallerRunsPolicy());
        for (Collection collection : multimap.asMap().values())
        {
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
                    }catch (Exception e){
                        threadException[0] =e;
                        executor.shutdownNow();//中断所有请求
                        while (latch.getCount()>0){latch.countDown();}
                    }
                    latch.countDown();
                }
            };
            executor.execute(run);
        }
        //如果设置了整体任务的执行超时时间后可以加此判断
        //latch.await(5,TimeUnit.SECONDS);
        latch.await();
        executor.awaitTermination(3,TimeUnit.SECONDS);
        executor.shutdown();
        if (threadException[0].getMessage()!=null){
            throw new IllegalStateException(threadException[0]);
        }
        if(result.size()!=multimapMapSize){
            throw new IllegalStateException("result.size()不等于multimapMapSize的大小，任务执行失败");
        }
        //此时你会发现任务完成的数量与multimapMapSize的大小不同，这可能是由于ThreadPoolExecutor内部的更新机制有关
        //但从result.size()的大小必须等于multimapMapSize的大小
        PrintUtil.outLn("executor.getCompletedTaskCount()-----" + executor.getCompletedTaskCount());
        PrintUtil.outLn("multimap.asMap().size()---" + multimapMapSize);
        PrintUtil.outLn("result.size()"+result.size());
        PrintUtil.outLn(result.toString());
        /*
        //如果设置了整体任务的执行超时时间后可以加此判断
        latch.await(5,TimeUnit.SECONDS);
        if(latch.getCount()>0){
            throw new IllegalStateException("任务执行超时");
        }*/
    }
}
