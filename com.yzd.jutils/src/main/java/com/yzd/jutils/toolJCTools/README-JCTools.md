# JCTools
- [https://github.com/JCTools/JCTools](https://github.com/JCTools/JCTools)
- [JCTools简介](https://www.jianshu.com/p/14dcfd84704f)
- [System.identityHashCode(obj) 与 obj.hashcode()](https://www.jianshu.com/p/24fa4bdb9b9d)

## [JCTools 简介]()
> JCTools (Java Concurrency Tools) 提供了一系列非阻塞并发数据结构（标准 Java 中缺失的），
当存在线程争抢的时候，非阻塞并发数据结构比阻塞并发数据结构能提供更好的性能。
JCTools 是一个开源工具包，在 Apache License 2.0 下发布，并在 Netty、Rxjava 等诸多框架中被广泛使用。
JCTools 的开源 Github 仓库：https://github.com/JCTools/JCTools

```
JCTools 中主要提供了 Map 以及 Queue 的非阻塞并发数据结构：

非阻塞 Map
ConcurrentAutoTable（后面几个map/set结构的基础）
NonBlockingHashMap
NonBlockingHashMapLong
NonBlockingHashSet
NonBlockingIdentityHashMap
NonBlockingSetInt
NonBlockingHashMap 是对 ConcurrentHashMap 的增强，对多 CPU 的支持以及高并发更新提供更好的性能。

NonBlockingHashMapLong 是 key 为 Long 型的 NonBlockingHashMap。

NonBlockingHashSet 是对 NonBlockingHashMap 的简单包装以支持 set 的接口。

NonBlockingIdentityHashMap 是从 NonBlockingHashMap 改造来的，使用 System.identityHashCode() 来计算哈希。

NonBlockingSetInt 是一个使用 CAS 的简单的 bit-vector。

非阻塞 Queue
JCTools 提供的非阻塞队列分为 4 类，可以根据不同的应用场景选择使用：

SPSC-单一生产者单一消费者（有界和无界）
MPSC-多生产者单一消费者（有界和无界）
SPMC-单生产者多消费者（有界）
MPMC-多生产者多消费者（有界）
“生产者”和“消费者”是指“生产线程”和“消费线程”。

        // spsc-有界/无界队列
        Queue<String> spscArrayQueue = new SpscArrayQueue(16);
        Queue<String> spscUnboundedArrayQueue = new SpscUnboundedArrayQueue(2);
        // spmc-有界队列
        Queue<String> spmcArrayQueue = new SpmcArrayQueue<>(16);
        // mpsc-有界/无界队列
        Queue<String> mpscArrayQueue = new MpscArrayQueue<>(16);
        Queue<String> mpscChunkedArrayQueue = new MpscChunkedArrayQueue<>(1024, 8 * 1024);
        Queue<String> mpscUnboundedArrayQueue = new MpscUnboundedArrayQueue<>(2);
        // mpmc-有界队列
        Queue<String> mpmcArrayQueue = new MpmcArrayQueue<>(16);

```