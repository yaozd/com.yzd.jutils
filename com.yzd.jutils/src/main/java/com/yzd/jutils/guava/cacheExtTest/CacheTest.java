package com.yzd.jutils.guava.cacheExtTest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试cache的性能，在同时读，写与缓存清除的情况下的性能的变化
 */
public class CacheTest {
    public static void main(String[] args) throws InterruptedException {
//        System.out.println("内存占用(kb)：");
//        System.out.println("数据量10k\t" + mem(10_000));
//        System.out.println("数据量100k\t" + mem(100_000));
//
//        System.out.println();
//
//        System.out.println("10_000_000个数据写入时间(ms)：");
//        System.out.println("注：横坐标是cache size，纵坐标是thread数量");
//        System.out.println("\t10_000\t100_0000");
//        System.out.println("1\t" + writeTime(1, 10_000, 10_000_000) + "\t" + writeTime(1, 100_000, 10_000_000));
//        System.out.println("8\t" + writeTime(8, 10_000, 10_000_000) + "\t" + writeTime(8, 100_000, 10_000_000));
//        System.out.println("16\t" + writeTime(16, 10_000, 10_000_000) + "\t" + writeTime(16, 100_000, 10_000_000));
//
//        System.out.println();
//
//        System.out.println("10_000_000个数据读取时间(ms)：");
//        System.out.println("注：横坐标是cache size，纵坐标是thread数量");
//        System.out.println("\t10_000\t100_0000");
//        System.out.println("1\t" + readTime(1, 10_000, 10_000_000) + "\t" + readTime(1, 100_000, 10_000_000));
//        System.out.println("8\t" + readTime(8, 10_000, 10_000_000) + "\t" + readTime(8, 100_000, 10_000_000));
//        System.out.println("16\t" + readTime(16, 10_000, 10_000_000) + "\t" + readTime(16, 100_000, 10_000_000));
//
//        System.out.println();
//
//        System.out.println("10_000_000个数据读取失败时间(ms)：");
//        System.out.println("注：横坐标是cache size，纵坐标是thread数量");
//        System.out.println("\t10_000\t100_0000");
//        System.out.println("1\t" + readFailedTime(1, 10_000, 10_000_000) + "\t" + readFailedTime(1, 100_000, 10_000_000));
//        System.out.println("8\t" + readFailedTime(8, 10_000, 10_000_000) + "\t" + readFailedTime(8, 100_000, 10_000_000));
//        System.out.println("16\t" + readFailedTime(16, 10_000, 10_000_000) + "\t" + readFailedTime(16, 100_000, 10_000_000));
        test1();

    }

    private static long mem(int n) {
        Runtime runtime = Runtime.getRuntime();
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder().build();
        long heapSize = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < n; i++) {
            cache.put(i, i);
        }
        long heapSize2 = runtime.totalMemory() - runtime.freeMemory();
        return (heapSize2 - heapSize) / 1000;
    }

    private static long writeTime(int nThread, int max, int nData) throws InterruptedException {
        long t = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(nThread);
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumSize(max).build();
        for (int i = 0; i < nThread; i++) {
            int start = nData / nThread * i;
            int end = nData / nThread * (i + 1) - 1;
            es.execute(() -> {
                for (int j = start; j <= end; j++) {
                    cache.put(j, j);
                }
            });
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.HOURS);
        return System.currentTimeMillis() - t;
    }

    private static long readTime(int nThread, int max, int nTimes) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(nThread);
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumSize(max).build();
        for (int i = 0; i < max; i++) {
            cache.put(i, i);
        }
        Callable<Integer> callable = new CustomCallable();
        long t = System.currentTimeMillis();
        int nTimesPerThread = nTimes / nThread;
        for (int i = 0; i < nThread; i++) {
            es.execute(() -> {
                for (int j = 0; j <= nTimesPerThread; j++) {
                    try {
                        cache.get(j % max, callable);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.HOURS);
        return System.currentTimeMillis() - t;
    }

    private static long readFailedTime(int nThread, int max, int nTimes) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(nThread);
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumSize(max).build();
        for (int i = 0; i < max; i++) {
            cache.put(i, i);
        }
        Callable<Integer> callable = new CustomCallable();
        long t = System.currentTimeMillis();
        int nTimesPerThread = nTimes / nThread;
        for (int i = 0; i < nThread; i++) {
            es.execute(() -> {
                for (int j = 0; j <= nTimesPerThread; j++) {
                    try {
                        cache.get(max + j, callable);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.HOURS);
        return System.currentTimeMillis() - t;
    }

    private static void test1() throws InterruptedException {
        ExecutorService writeEs = Executors.newFixedThreadPool(8);
        ExecutorService readEs = Executors.newFixedThreadPool(8);
        //Cache<Integer, Integer> cache = CacheBuilder.newBuilder().maximumSize(100_000).expireAfterWrite(Duration.ofMillis(50)).build();
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(100_000).expireAfterWrite(50, TimeUnit.MILLISECONDS).build();
        CustomCallable customCallable = new CustomCallable();
        AtomicLong writeCount = new AtomicLong();
        AtomicLong readCount = new AtomicLong();
        for (int i = 0; i < 8; i++) {
            writeEs.execute(() -> {
                while (true) {
                    cache.put(ThreadLocalRandom.current().nextInt(0, 200_000),
                            ThreadLocalRandom.current().nextInt(0, 200_000));
                    writeCount.getAndIncrement();
                }
            });
        }
        for (int i = 0; i < 8; i++) {
            readEs.execute(() -> {
                try {
                    while (true) {
                        cache.get(ThreadLocalRandom.current().nextInt(0, 200_000), customCallable);
                        readCount.getAndIncrement();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(100_000);
//        writeEs.shutdownNow();
//        readEs.shutdownNow();
        System.out.println("耗时：100s，readCount: " + readCount.get() + "，writeCount：" + writeCount.get());
        System.exit(0);


    }

    private static class CustomCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }
}