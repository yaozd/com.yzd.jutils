package com.yzd.jutils.threadExt4Synchronous.task;

import com.yzd.jutils.threadExt4Synchronous.config.TaskEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 拉取任务，执行模式：串行
 * 功能：从MQ中拉取任务,然后把数据添加到本地的任务队列中
 */
@Slf4j
public class Worker4PullTask {

    public void doWork() {
        log.info(Thread.currentThread().getName());

        for (int i = 0; i < 1000; i++) {
            String data = "1-" + i;
            //功能：从MQ中拉取任务,然后把数据添加到本地的任务队列中
            TaskEnum.PAY.sendData(data);
        }

    }
}
