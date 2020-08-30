package com.yzd.jutils.algorithmExt.ForkJoinPoolExt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 计算1亿个整数的和
 */
public class ForkJoinPoolTest01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 构造数据
        int length = 100000000;
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        }
        // 单线程
        singleThreadSum(arr);
        // ThreadPoolExecutor线程池
        multiThreadSum(arr);
        // ForkJoinPool线程池
        forkJoinSum(arr);
        System.out.println("END");

    }

    private static void singleThreadSum(long[] arr) {
        long start = System.currentTimeMillis();

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // 模拟耗时，本文由公从号“彤哥读源码”原创
            sum += (arr[i]/3*3/3*3/3*3/3*3/3*3);
        }

        System.out.println("sum: " + sum);
        System.out.println("single thread elapse: " + (System.currentTimeMillis() - start));

    }

    private static void multiThreadSum(long[] arr) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        int count = 8;
        ExecutorService threadPool = Executors.newFixedThreadPool(count);
        List<Future<Long>> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int num = i;
            // 分段提交任务
            Future<Long> future = threadPool.submit(() -> {
                long sum = 0;
                for (int j = arr.length / count * num; j < (arr.length / count * (num + 1)); j++) {
                    try {
                        // 模拟耗时
                        sum += (arr[j]/3*3/3*3/3*3/3*3/3*3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return sum;
            });
            list.add(future);
        }

        // 每个段结果相加
        long sum = 0;
        for (Future<Long> future : list) {
            sum += future.get();
        }

        System.out.println("sum: " + sum);
        System.out.println("multi thread elapse: " + (System.currentTimeMillis() - start));
    }

    private static void forkJoinSum(long[] arr) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        // 提交任务
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(new SumTask(arr, 0, arr.length));
        // 获取结果
        Long sum = forkJoinTask.get();

        forkJoinPool.shutdown();

        System.out.println("sum: " + sum);
        System.out.println("fork join elapse: " + (System.currentTimeMillis() - start));
    }

    private static class SumTask extends RecursiveTask<Long> {
        private long[] arr;
        private int from;
        private int to;

        public SumTask(long[] arr, int from, int to) {
            this.arr = arr;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 小于1000的时候直接相加，可灵活调整
            if (to - from <= 1000) {
                long sum = 0;
                for (int i = from; i < to; i++) {
                    // 模拟耗时
                    sum += (arr[i]/3*3/3*3/3*3/3*3/3*3);
                }
                return sum;
            }

            // 分成两段任务，本文由公从号“彤哥读源码”原创
            int middle = (from + to) / 2;
            SumTask left = new SumTask(arr, from, middle);
            SumTask right = new SumTask(arr, middle, to);

            // 提交左边的任务
            left.fork();
            // 右边的任务直接利用当前线程计算，节约开销
            Long rightResult = right.compute();
            // 等待左边计算完毕
            Long leftResult = left.join();
            // 返回结果
            return leftResult + rightResult;
        }
    }
}