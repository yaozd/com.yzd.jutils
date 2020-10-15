# top k

## PS:
- 大数据流式计算，使用CountMinSketch算法
    - [实时计算工具库 stream-lib 使用指南](https://blog.csdn.net/zjerryj/article/details/77628694)
        - HyperLogLog 唯一值计算  独立访客（UV）是网站的重要指标之一
        - BloomFilter 用于检测一个元素是否包含在集合中 特点是有一定几率误报（False Positive Probability, FPP），但绝不会漏报（False Negative）
        - CountMinSketch Top K 排名
        - T-Digest 分位数计算 TP95
        - obyCount 大数据算法-重复率计算 ICardinality
        - 等等
- 测试实验：小顶堆  (感觉TP95就可以使用最小堆算法)
    - [面试手撕代码常见TopK问题（Java小顶堆实现）](https://blog.csdn.net/qq_30242987/article/details/104800716) 推荐参考
    - [五分钟学算法：Top K 问题的两种经典解法](https://blog.csdn.net/kexuanxiu1163/article/details/105548608) 
    - [大数据量获取TopK的几种方案](https://liyangyang.blog.csdn.net/article/details/82909081)
    - PS:
    ```
    如果把数据看成输入流的话，使用堆的方法是来一个处理一个，不需要保存数据，只需要保存 k 个元素的最大堆。
    当数据量大的时候还是用基于堆的方法比较好
    ```
- [基本算法——BFPRT线性查找算法](https://www.jianshu.com/p/0fb557f3c412)