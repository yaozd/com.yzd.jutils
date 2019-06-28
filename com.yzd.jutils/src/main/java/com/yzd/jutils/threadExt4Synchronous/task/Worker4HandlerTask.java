package com.yzd.jutils.threadExt4Synchronous.task;

import com.yzd.jutils.threadExt4Synchronous.config.AbstractWorker;
import com.yzd.jutils.threadExt4Synchronous.config.TaskEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 处理任务，执行模式：并行
 */
@Slf4j
public class Worker4HandlerTask extends AbstractWorker {
    //待处理的数据
    private String data;
    /**
     *
     * @param taskEnum 任务名
     * @param data 待处理的数据
     */
    public Worker4HandlerTask(TaskEnum taskEnum, String data) {
        super(taskEnum);
        this.data=data;
    }
    @Override
    protected void doWork() {
        log.info(Thread.currentThread().getName());
        try {
            //具体的业务处理逻辑
            //任务操作异常或数据库异常
            TimeUnit.SECONDS.sleep(1);
            //TimeUnit.SECONDS.sleep(3);
        } catch (Exception ex) {
            //log ex
        }
    }
}