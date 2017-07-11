package com.yzd.jutils.blockingQueue;

import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列
 * 似懂非懂的SynchronousQueue和长度为1的BlockingQueue
 *  http://blog.csdn.net/aitangyong/article/details/38684831
 *  使用场景：用于分布式消息队列中-多个redis的数据源数据读取结果进行合并
 * Created by zd.yao on 2017/7/6.
 */
public class SyncQueueTester3 {
    private static SynchronousQueue<String> queue = new SynchronousQueue<String>();
    public static void main(String[] args) throws Exception
    {
        //测试同步阻塞队列必须使用多线程的方式
        new Productor(1).start();
        new Productor(2).start();
        //new Consumer().start();
        System.out.println("main begin.");
        int i=0;
        while (true)
        {
            //System.out.println("consume begin.");
            //String v=queue.poll(5, TimeUnit.SECONDS);
            String v = queue.take();
            //System.out.println("consume success." + v);
            i++;
        }
    }

    static class Productor extends Thread
    {
        private int id;

        public Productor(int id)
        {
            this.id = id;
        }

        @Override
        public void run()
        {
            try
            {
                String result = "id=" + this.id;
                System.out.println("begin to produce."+result);
                queue.put(result);
                System.out.println("success to produce."+result);
                while (true){
                    //TimeUnit.MILLISECONDS.sleep(2);
                    queue.put(result);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                System.out.println("consume begin.");
                String v = queue.take();
                System.out.println("consume success." + v);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
