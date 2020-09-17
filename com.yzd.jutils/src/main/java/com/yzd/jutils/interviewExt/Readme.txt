java 面试题-20180529
问题1：HashMap和HashTable的区别？
答：


问题2：equals 和 == 的区别？输出结果？
Long userId=127L;
Long authorId=127L;
System.out.println(userId==authorId);
userId=128L;
authorId=128L;
System.out.println(userId==authorId);
Long x=new Long(127);
Long y=new Long(127);
System.out.println(x==y);
System.out.println(x.equals(y));
答：
true
false
false
true




问题3：下面语句的输出结果？
package com.bird.classLoad;
public class Test1 {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Singleton s = Singleton.getSingleton();
		System.out.println("counter1 = "+ s.counter1);
		System.out.println("counter2 = "+s.counter2);
	}
}
class Singleton{
	private static Singleton singleton = new Singleton();
	public static int counter1;
	public static int counter2 = 0;
	public Singleton(){
		counter1++;
		counter2++;
	}
	public static Singleton getSingleton(){
		return singleton;
	}
}
答：
counter1 = 1
counter2 = 0


问题4：ArrayBlockingQueue, CountDownLatch类的作用与使用场景
答：

问题5：mysql的事务四个特性以及事务的四个隔离级别
https://blog.csdn.net/csdnxingyuntian/article/details/57081233
spring的4种事务特性，5种隔离级别，7种传播行为
https://blog.csdn.net/weixin_38070406/article/details/78157603?utm_source=blogkpcl3


问题6：集合的默认初始容量、加载因子、扩容增量
http://www.cnblogs.com/lanzhi/p/6467269.html
Map是一个双列集合
==
HashMap：默认初始容量为16
（为何是16：16是2^4，可以提高查询效率，另外，32=16<<1       -->至于详细的原因可另行分析，或分析源代码）
加载因子为0.75：即当 元素个数 超过 容量长度的0.75倍 时，进行扩容
扩容增量：原容量的 1 倍
如 HashSet的容量为16，一次扩容后是容量为32
==
Java和guava关于hashmap在初始化的时候最好给个初始容量，避免扩容引起性能问题的探究。
https://blog.csdn.net/qq_27093465/article/details/52401308
guava里面有工具类Maps，可以很方便的创建一个集合，并且，带上合适的大小初始化值。具体如下：

Map<String, Object> map = Maps.newHashMapWithExpectedSize(7);

问题7：[BigDecimal 执行精确小数计算](https://www.cnblogs.com/winner-0715/p/6761168.html)
BigDecimal使用非String构造方法初始化，会出现精度缺失问题。
- 解决方法一：API 建议优先使用 String 构造方法【必须使用String】
- PS:千万不能随随便便使用 BigDecimal(double) 构造器来创建 BigDecimal 对象，因为该构造器是根据它的参数的精确值来创建实例对象的，
  该构造方法的结果还是有一定的不可预知性
```
错误使用一：
public class Test {
    public static void main(String[] args) {
        System.out.println(0.4 + 0.8); // = 1.2 ?
        System.out.println(2 - 1.1);   // = 0.9 ?
        System.out.println(0.2 * 3);  // = 0.6 ?
        System.out.println(1.2 / 3); // = 0.4 ?
    }
}
----------------------
错误使用二：
public class Test {
    public static void main(String[] args) {
        System.out.println(new BigDecimal(0.4).add(new BigDecimal(0.8)));// = 1.2 ?
        System.out.println(new BigDecimal(2).subtract(new BigDecimal(1.1)));// = 0.9 ?
        System.out.println(new BigDecimal(0.2).multiply(new BigDecimal(3)));// = 0.6 ?
        System.out.println(new BigDecimal(1.2).divide(new BigDecimal(3)));// = 0.4 ?
    }
}
----------------------
正确使用二：
public class Test {
    public static void main(String[] args) {

        System.out.println(new BigDecimal("0.4").add(new BigDecimal("0.8")));  // = 1.2 √
        System.out.println(new BigDecimal("2").subtract(new BigDecimal("1.1"))); // = 0.9 √
        System.out.println(new BigDecimal("0.2").multiply(new BigDecimal("3")));// = 0.6 √
        System.out.println(new BigDecimal("1.2").divide(new BigDecimal("3"))); // = 0.4 √

    }
}
----------------------
```
问题8：int long那些操作是原子操作吗？
常见的说法就是说int类型没问题，但long类型8字节，可能一个线程赋值时，刚写完4字节，另一线程就已经读取了，所以说不是原子操作。
参考：
问二十五：long、double是原子操作吗？
https://blog.csdn.net/qq_18657175/article/details/89784666

问题9：spring是怎么对对象生命周期做管理的?
Spring对对象生命周期的依次分为：

对象的创建（Construct）
对象的初始化前（BeforeInit)
对象创建之后(PostConstruct)
对象的初始化（Init)
对象的初始化后（AfterInit)
对象销毁前（PreDestroy）
对象的销毁（Destroy)
链接：https://www.jianshu.com/p/d1dfdb240617

问题10：synchroized和Reentranlock的区别
总的来说，使用最为简单的是synchronized同步方法，在JDK1.5之前synchronized的性能确实跟不上ReentranLock，但是后面对它改造之后，性能上已经不再是瓶颈；
而相对来说ReentranLock、ReentranReadWriteLock的使用则更加灵活，但是需要注意的点也相对多一些。

原文：synchronized同步方法、ReentranLock、ReentranReadWriteLock对比分析
链接：https://www.jianshu.com/p/5ef494c9a266

问题11：ConcurrentHashMap原理分析？
线程不安全的HashMap
    因为多线程环境下，使用Hashmap进行put操作会引起死循环，导致CPU利用率接近100%，所以在并发情况下不能使用HashMap。
效率低下的HashTable容器
     HashTable容器使用synchronized来保证线程安全，但在线程竞争激烈的情况下HashTable的效率非常低下。因为当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，可能会进入阻塞或轮询状态。如线程1使用put进行添加元素，线程2不但不能使用put方法添加元素，并且也不能使用get方法来获取元素，所以竞争越激烈效率越低。
锁分段技术
ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成
链接：https://www.cnblogs.com/ITtangtang/p/3948786.html

问题12：java 详解类加载器的双亲委派及打破双亲委派
https://blog.csdn.net/chang_ge/article/details/80262115

问题13：了解过哪些多线程并发工具，简单说一下？
 CountDownLatch
 CyclicBarrier
 Semaphore控制并发线程数的
 Exchanger（交换者）是一个用于线程间协作的工具类

 CyclicBarrier和CountDownLatch的区别
 CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重置，
 CountDownLatch.await一般阻塞主线程，所有的工作线程执行countDown，
 而CyclicBarrierton通过工作线程调用await从而阻塞工作线程，直到所有工作线程达到屏障。

 https://www.cnblogs.com/leeSmall/p/8439263.html

 问题14：多线程callable和runnable的区别

Thread、Runnable、Callable接口实现多线程的区别
观察以上三种实现方式和输出的结果可得
1.继承Thread方式，每次new Thread都是独立的，资源不共享,而Runnable资源共享；
2.实现Callable接口方式，只能运行一次FutureTask
Thread类与Runnable接口实现多线程的区别
1.Thread类是Runnable接口的子类，使用runnable接口实现多线程可以避免单继承局限；
2.Runnable接口实现的多线程可以比Thread类实现的多线程更加清楚的描述数据共享的概念。
如何理解：
因为一个线程只能启动一次，通过Thread实现线程时，线程和线程所要执行的任务是捆绑在一起的。也就使得一个任务只能启动一个线程，不同的线程执行的任务是不相同的，所以没有必要，也不能让两个线程共享彼此任务中的资源。
通过Runnable方式实现的线程，实际是开辟一个线程，将任务传递进去，由此线程执行。可以实例化多个 Thread对象，将同一任务传递进去，也就是一个任务可以启动多个线程来执行它。这些线程执行的是同一个任务，所以他们的资源是共享。
Calleble接口
1.最大的特点就是可以通过FutureTask获得线程核心处理方法的返回值(run方法是无返回值的
2.get方法会阻塞主线程来等待任务完成。FutureTask非常适合用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果
FutureTask被多次调用，依然只会执行一次Runnable任务
参考：
https://www.cnblogs.com/qiutianyou/p/10056465.html

问题15：创建线程池每个参数有什么作用
corePoolSize：
默认线程数量（核心线程数量），在创建线程池之后，线程池里没有任何线程，等到有任务进来时才创建线程去执行任务（懒加载）。当线程池中的线程数达到corePoolSize的值后，就会把到达的任务放到缓存队列里；
maximumPoolSize：
最大线程数量，表明线程中最多能创建的线程数量。当核心线程+非核心线程达到这个数值后，后续任务将会根据RejectedExecutionHandler处理器来进行饱和策略处理；
keepAliveTime：
非核心线程闲置时的存活时间，超过该时长，非核心线程就会被回收；
TimeUnit：
keepAliveTime时长对应的单位（天、小时、分钟、秒、毫秒、微妙、纳秒）；
BlockingQueue：
阻塞队列，存储等待执行的任务；
ThreadFactory：
线程工厂，用来创建线程；
RejectedExecutionHandler：
队列已满，而且任务量大于最大线程数量的异常处理策略
---------------------
原文：https://blog.csdn.net/sinat_40880767/article/details/93890900

问题16：Java中sleep方法和wait方法的区别---线程
最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法(锁代码块和方法锁)。
sleep方法属于Thread类中方法，表示让一个线程进入睡眠状态，等待一定的时间之后，自动醒来进入到可运行状态，不会马上进入运行状态
wait属于Object的成员方法，一旦一个对象调用了wait方法，必须要采用notify()和notifyAll()方法唤醒该进程
---------------------
原文：https://blog.csdn.net/krismile__qh/article/details/89024378

问题17：synchronized(this) 与synchronized(class) 之间的区别
1、对于静态方法，由于此时对象还未生成，所以只能采用类锁；

2、只要采用类锁，就会拦截所有线程，只能让一个线程访问。

3、对于对象锁（this），如果是同一个实例，就会按顺序访问，但是如果是不同实例，就可以同时访问。

4、如果对象锁跟访问的对象没有关系，那么就会都同时访问。
---------------------
原文：https://www.cnblogs.com/huansky/p/8869888.html

问题18：ThreadLocal内存泄漏原因以及避免方案
但如果是在线程池中，线程执行完后不被回收，而是返回线程池中，Thread有个强引用指向ThreadLocalMap,ThreadLocalMap有强引用指向Entry,导致value无法被回收，一直存在内存中。在执行了ThreadLocal.set()方法之后一定要记得使用ThreadLocal.remove(),将不要的数据移除掉，避免内存泄漏。
---------------------
原文：https://blog.csdn.net/wy1404081737/article/details/81489080

---------------------
动态代理:
CGLib方式对接口实现代理
JDK与CGLib的区别：
JDK动态代理的原理是根据定义好的规则，用传入的接口创建一个新类，这就是为什么采用动态代理时为什么只能用接口引用指向代理，而不能用传入的类引用执行动态类。
CGLib采用的是用创建一个继承实现类的子类，用asm库动态修改子类的代码来实现的，所以可以用传入的类引用执行代理类
使用场景：
CGLib创建的动态代理对象性能比JDK创建的动态代理对象的性能高不少，但是CGLib在创建代理对象时所花费的时间却比JDK多得多，所以对于单例的对象，
因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。同时，由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理
原文：
CGLib方式对接口实现代理
https://www.cnblogs.com/chenliangcl/p/7239709.html