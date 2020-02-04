package com.yzd.jutils.threadExt4Synchronous.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

@Slf4j
public enum TaskEnum {
    PAY(5);

    TaskEnum(Integer maxThreadSize) {
        /**
         * 因为：
         * 先把数据放入到本地任务队列中,再添加一个令牌到本地的令牌桶中
         * 所以：
         * 实际的令牌桶的容量为maxThreadSize-1
         */
        this.tokenBucket = new ArrayBlockingQueue<Integer>(maxThreadSize - 1);
        this.data = new SynchronousQueue<String>(true);
    }

    //作用：控制线程数
    private ArrayBlockingQueue<Integer> tokenBucket;
    //作用：待处理数据传输
    private SynchronousQueue<String> data;

    private void putData(String data) {
        try {
            this.data.put(data);
        } catch (InterruptedException e) {
            if (TaskUtil.isShutdown()) {
                return;
            }
            throw new IllegalStateException(e);
        }
    }

    private void putToken() {
        try {
            this.tokenBucket.put(1);
        } catch (InterruptedException e) {
            if (TaskUtil.isShutdown()) {
                return;
            }
            throw new IllegalStateException(e);
        }
    }

    /**
     * 从本地任务队列中读取数据
     *
     * @return
     */
    public String takeData() {
        try {
            return this.data.take();
        } catch (InterruptedException e) {
            if (TaskUtil.isShutdown()) {
                return null;
            }
            throw new IllegalStateException(e);
        }
    }

    /**
     * 释放一个令牌
     */
    public void pollToken() {
        this.tokenBucket.poll();
    }

    /**
     * 把数据放入到本地任务队列中,再添加一个令牌到本地的令牌桶中
     *
     * @param data
     */
    public void sendData(String data) {
        //先把数据放入到本地任务队列中
        putData(data);
        //再添加一个令牌到本地的令牌桶中
        putToken();
    }

    /**
     * 令牌桶的当前大小
     *
     * @return
     */
    public int getTokenBucketSize() {
        return this.tokenBucket.size();
    }
}