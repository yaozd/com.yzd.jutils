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
    //推荐参考byArvin-2019-03-22
    //批量导入到InfluxDb
    //https://github.com/yaozd/com.yzd.influxdb.root.git
    //InfluxDBUtil4BatchInsertTest.batchInsert()

    //BatchInsertTest:批量插入数据到数据库（influxdb或者mysql）

    //通过PerfTest制定并发线程数和invocations指定调用的次数。
    //@PerfTest(threads = 1,invocations=10000)
    //@PerfTest(threads = 1,duration=1000000)
    /**
     * 批量插入
     * 目前推荐使用此方法-byArvin-20190321-1146
     * 解决批量插入中因使用阻塞队列take而产生大量线程驻留问题
     * @throws InterruptedException
     */
    @Test
    //@PerfTest(threads = 20, duration = 1000000)
    public void batchInsertByBlockingQueue2() {
        for (int i = 0; i < 200; i++) {
            //数据可以通过同步阻塞队列
            DataRepository.PRODUCT.putData(String.valueOf(i));
        }
        //读取数据
        List<String> data = DataRepository.PRODUCT.batchData(100,1);
        log.info("data size="+data.size());
        //转换数据

        //批量插入到influxdb

    }

    /**
     * 条件：执行时间最大为1秒或者读取1000条数据。
     *
     * @return
     */
    private List<String> getBatchData() {
        List<String> data = new ArrayList<>();
        boolean firstEmpty=true;
        for (int i = 0; i < 250; i++) {
            String value = DataRepository.PRODUCT.takeData();
            //数据可以通过同步阻塞队列或者是Redis的消息队列等
            //第一次读取不到数据，则直接等待1秒
            if (value == null && firstEmpty) {
                //log.info(null);
                try {
                    //通过休眠，代表等待1秒，执行时间最大为1秒。
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                firstEmpty=false;
                continue;
            }
            //第二次读取不到数据，则直接返回
            if(value==null&&!firstEmpty){
                break;
            }
            data.add(value);
        }
        return data;
    }
}
