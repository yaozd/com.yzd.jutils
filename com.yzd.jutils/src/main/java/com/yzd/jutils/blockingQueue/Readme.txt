#
BlockingQueue的使用
https://www.cnblogs.com/liuling/p/2013-8-20-01.html
本例介绍一个特殊的队列:BlockingQueue,如果BlockQueue是空的,从BlockingQueue取东西的操作将会被阻断进入等待状态,直到BlockingQueue进了东西才会被唤醒.同样,如果BlockingQueue是满的,任何试图往里存东西的操作也会被阻断进入等待状态,直到BlockingQueue里有空间才会被唤醒继续操作.
    使用BlockingQueue的关键技术点如下:
    1.BlockingQueue定义的常用方法如下:
        1)add(anObject):把anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则报异常
        2)offer(anObject):表示如果可能的话,将anObject加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则返回false.
        3)put(anObject):把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
        4)poll(time):取走BlockingQueue里排在首位的对象,若不能立即取出,则可以等time参数规定的时间,取不到时返回null
        5)take():取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到Blocking有新的对象被加入为止
    2.BlockingQueue有四个具体的实现类,根据不同需求,选择不同的实现类
        1)ArrayBlockingQueue:规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(先入先出)顺序排序的.
        2)LinkedBlockingQueue:大小不定的BlockingQueue,若其构造函数带一个规定大小的参数,生成的BlockingQueue有大小限制,若不带大小参数,所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定.其所含的对象是以FIFO(先入先出)顺序排序的
        3)PriorityBlockingQueue:类似于LinkedBlockQueue,但其所含对象的排序不是FIFO,而是依据对象的自然排序顺序或者是构造函数的Comparator决定的顺序.
        4)SynchronousQueue:特殊的BlockingQueue,对其的操作必须是放和取交替完成的.
    3.LinkedBlockingQueue和ArrayBlockingQueue比较起来,它们背后所用的数据结构不一样,导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue,但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue.

下面是两个使用BlockingQueue的例子：
package com.thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        for(int i=0;i<2;i++){
            new Thread(){
                public void run(){
                    while(true){
                        try {
                            Thread.sleep((long)(Math.random()*1000));
                            System.out.println(Thread.currentThread().getName() + "准备放数据!");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName() + "已经放了数据，" +
                                        "队列目前有" + queue.size() + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }.start();
        }

        new Thread(){
            public void run(){
                while(true){
                    try {
                        //将此处的睡眠时间分别改为100和1000，观察运行结果
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据!");
                        queue.take();
                        System.out.println(Thread.currentThread().getName() + "已经取走数据，" +
                                "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }
}
======================================
package com.thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueCondition {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        final Business3 business = new Business3();
        service.execute(new Runnable(){

            public void run() {
                for(int i=0;i<50;i++){
                    business.sub();
                }
            }

        });

        for(int i=0;i<50;i++){
            business.main();
        }
    }

}

class Business3{
    BlockingQueue subQueue = new ArrayBlockingQueue(1);
    BlockingQueue mainQueue = new ArrayBlockingQueue(1);
    //这里是匿名构造方法，只要new一个对象都会调用这个匿名构造方法，它与静态块不同，静态块只会执行一次，
    //在类第一次加载到JVM的时候执行
    //这里主要是让main线程首先put一个，就有东西可以取，如果不加这个匿名构造方法put一个的话程序就死锁了
    {
        try {
            mainQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void sub(){
        try
        {
            mainQueue.take();
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
            subQueue.put(1);
        }catch(Exception e){

        }
    }

    public void main(){

        try
        {
            subQueue.take();
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
            mainQueue.put(1);
        }catch(Exception e){
        }
    }
}
