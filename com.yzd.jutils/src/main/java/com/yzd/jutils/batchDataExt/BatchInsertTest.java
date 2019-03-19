package com.yzd.jutils.batchDataExt;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BatchInsertTest {

    //BatchInsertTest:批量插入数据到数据库（influxdb或者mysql）

    //通过PerfTest制定并发线程数和invocations指定调用的次数。
    @Test
    //@PerfTest(threads = 1,invocations=10000)
    //@PerfTest(threads = 1,duration=1000000)
    public void readBatchData() throws InterruptedException {
        List<String> data=getBatchData();
        log.info("data.size()="+data.size());
    }
    /**
     * 条件：执行时间最大为1秒或者读取1000条数据。
     * @return
     */
    private List<String> getBatchData(){
        List<String> data=new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                //数据可以通过同步阻塞队列或者是Redis的消息队列等
                data.add(String.valueOf(i));
            }
        });
        //解决线程驻留问题》executorService.shutdown();+executorService.awaitTermination(1,TimeUnit.SECONDS);
        //如果没有执行shutdown就会出现线程驻留无法回收问题。
        executorService.shutdown();
        try {
            executorService.awaitTermination(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }
}
