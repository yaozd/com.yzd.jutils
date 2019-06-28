package com.yzd.jutils.threadExt4Synchronous;

import com.yzd.jutils.threadExt4Synchronous.config.TaskEnum;
import com.yzd.jutils.threadExt4Synchronous.config.TaskUtil;
import com.yzd.jutils.threadExt4Synchronous.task.Worker4HandlerTask;
import com.yzd.jutils.threadExt4Synchronous.task.Worker4PullTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class ThreadExt4SynchronousTest {

    @Test
    public void threadPool_Test() {
        //------------------------------拉取任务（单线程）-------------------------------------------//
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            Worker4PullTask pullTask=new Worker4PullTask();
            pullTask.doWork();
        });
        //------------------------------处理任务(多线程)-------------------------------------------//
        //ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        TaskEnum taskEnum=TaskEnum.PAY;
        ThreadPoolExecutor executor = TaskUtil.newThreadPoolExecutor(taskEnum.name());
        while (true) {
            if (TaskUtil.isShutdown()) {
                return;
            }
            //从本地任务队列中读取任务
            String data = taskEnum.takeData();
            log.info("Take=" + data);
            if (data == null) {
                continue;
            }
            Worker4HandlerTask worker4ReadTask=new Worker4HandlerTask(taskEnum,data);
            executor.execute(worker4ReadTask);
        }
    }
}