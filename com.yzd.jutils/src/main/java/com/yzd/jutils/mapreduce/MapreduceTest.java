package com.yzd.jutils.mapreduce;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yzd.jutils.print.PrintUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zd.yao on 2017/6/22.
 */
public class MapreduceTest {
    /***
     * 建议只用于查询操作或者计算操作，如果是更新插入则不可用
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++){
            T1();
        }
    }

    private static void T1() throws InterruptedException {
        //生成测试数据
        List<Integer> userIdList = new ArrayList<>();
        for (int i = 0; i < 101; i++){
            userIdList.add(i);
        }
        //任务分组--每2个做为一个任务
        final int groupSize=2;
        Multimap<Integer, Integer> multimap = ArrayListMultimap.create();
        Integer userIdListSize = userIdList.size();
        for (Integer i = 0; i < userIdListSize; i++) {
            multimap.put(i / groupSize, userIdList.get(i));
        }
        //并发执行任务
        int multimapMapSize = multimap.asMap().size();
        CountDownLatch latch = new CountDownLatch(multimapMapSize);
        final int threadSize=13;
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);
        List<Future<Integer>> futureResult = Lists.newArrayList();
        for (Collection collection : multimap.asMap().values()) {
            MapreduceTask task = new MapreduceTask(Lists.newArrayList(collection), latch);
            Future<Integer> future = executor.submit(task);
            futureResult.add(future);
        }
        latch.await();
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        //合并结果数据集
        List<Integer> finalResult = Lists.newArrayList();
        try {
            for (Future<Integer> future : futureResult) {
                Integer value = future.get();
                if (value == null) {
                    throw new IllegalStateException("future.get()值等于null");
                }
                finalResult.add(future.get());
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        PrintUtil.outLn("multimapMapSize=" + multimapMapSize);
        PrintUtil.outLn("finalResult.size()=" + finalResult.size());
        if (finalResult.size() != multimapMapSize) {
            String error="finalResult.size()=" + finalResult.size()+"multimapMapSize=" + multimapMapSize+"：finalResult.size()不等于multimapMapSize的大小，任务执行失败";
            throw new IllegalStateException(error);
        }
        //显示最终结果
        PrintUtil.outLn(finalResult.toString());
    }
}
