package com.yzd.jutils.threadExt4Synchronous.config;

public abstract class AbstractWorker implements Runnable  {
    private TaskEnum tokenBucket;

    public AbstractWorker(TaskEnum tokenBucket) {
        this.tokenBucket = tokenBucket;
    }

    @Override
    public void run() {
        try {
            doWork();
        } finally {
            tokenBucket.pollToken();
        }
    }
    protected void doWork() {
        //具体的业务逻辑代码
        throw new IllegalStateException("【AbstractTask】没有具体的业务逻辑实现代码");
    }
}
