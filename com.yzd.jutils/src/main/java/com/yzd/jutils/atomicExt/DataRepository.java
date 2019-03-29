package com.yzd.jutils.atomicExt;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public enum DataRepository {
    PRODUCT(1000);

    //--------------------------------------
    //参考：
    //10.并发包阻塞队列之ArrayBlockingQueue
    //

    /**
     * 数据传输
     *
     * @param maxSize 队列传输的最大容量
     */
    DataRepository(int maxSize) {
        this.data = new ArrayBlockingQueue<String>(maxSize);
        ;
    }

    private ArrayBlockingQueue<String> data;

    /**
     * 插入数据
     *
     * @param data
     */
    public void putData(String data) {
        //offer-非阻塞
        //offer(e)//队列未满时，返回true；队列满时返回false。非阻塞立即返回。
        this.data.offer(data);
        //data.put()-阻塞,如果使用不好，会产生大量线程驻留。
       /* try {
            this.data.put(data);
        } catch (InterruptedException e) {
            //目前默认这里吃掉中断异常
            //log.error("[InterruptedException:]",e);
        }*/
    }

    /**
     * 取数据
     *
     * @return
     */
    public String takeData() {
        //poll-非阻塞
        return this.data.poll();
        //data.take()-阻塞,如果使用不好，会产生大量线程驻留。
        /*String value=null;
        try {
            value=this.data.take();
        } catch (InterruptedException e) {
            //目前默认这里吃掉中断异常
            //log.error("[InterruptedException:]",e);
        }
        return value;*/
    }

    /**
     * 批量读取数据-通过阻塞队列
     * @param maxSize
     * @param sleep
     * @return
     */
    public List<String> batchData(int maxSize,int sleep) {
        List<String> data = new ArrayList<>();
        boolean firstEmpty = true;
        for (int i = 0; i < maxSize; i++) {
            String value = takeData();
            //第一次读取不到数据，则直接等待1秒
            if (value == null && firstEmpty) {
                try {
                    //通过休眠，代表等待1秒，执行时间最大为1秒。
                    TimeUnit.SECONDS.sleep(sleep);
                } catch (InterruptedException e) { }
                firstEmpty = false;
                continue;
            }
            //第二次读取不到数据，则直接返回
            if (value == null && !firstEmpty) {
                break;
            }
            data.add(value);
        }
        return data;
    }
    //region Pkg的作用主要是用于防止influxdb,批量导入数据丢失问题
    //Pkg的作用主要是用于防止influxdb,批量导入数据丢失问题
    AtomicInteger pkgId = new AtomicInteger(0);
    //目前暂定1万的pkg
    int maxPkgId=10000;
    /**
     * pkgId到达最大值maxPkgId后重置pkgId为零。
     * synchronized作用：AtomicInteger实现边界值控制
     *      * 解决：
     *      * 最佳实践：AtomicInteger实现边界值控制
     *      * 使用synchronized 保证顺序，对性能基本没有影响
     * @return
     */
    public synchronized int incrementAndGet() {
        int value= pkgId.incrementAndGet();
        if(value>maxPkgId){
            pkgId.set(0);
            value=pkgId.incrementAndGet();
            //pkgIdResetCount.incrementAndGet();
        }
        return value;
    }
    //pkg重置的次数。
    AtomicInteger pkgIdResetCount = new AtomicInteger(0);

    /**
     * 此方法是验证incrementAndGet的
     */
    public void printTest(){
        System.out.println(pkgIdResetCount.get());
    }
    //end
}
