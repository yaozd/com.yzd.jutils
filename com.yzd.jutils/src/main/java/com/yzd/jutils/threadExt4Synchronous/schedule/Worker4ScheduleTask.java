package com.yzd.jutils.threadExt4Synchronous.schedule;

import com.yzd.jutils.threadExt4Synchronous.config.TaskEnum;
import com.yzd.jutils.threadExt4Synchronous.config.TaskUtil;
import com.yzd.jutils.threadExt4Synchronous.task.Worker4HandlerTask;
import com.yzd.jutils.threadExt4Synchronous.task.Worker4PullTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
public class Worker4ScheduleTask {
    /**
     * 拉取任务
     * @throws InterruptedException
     */
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_writeQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("拉取任务：" + dateFormat.format(new Date()));
        Worker4PullTask pullTask = new Worker4PullTask();
        pullTask.doWork();
    }

    /**
     * 处理任务
     * @throws InterruptedException
     */
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_readQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        log.info("处理任务: " + dateFormat.format(new Date()));
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
