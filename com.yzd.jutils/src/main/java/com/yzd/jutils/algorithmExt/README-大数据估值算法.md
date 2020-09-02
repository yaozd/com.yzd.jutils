## 大数据估值算法-stream-lib
- [https://github.com/addthis/stream-lib](https://github.com/addthis/stream-lib)
    - [t-Digest算法在异常检测上的应用](https://blog.csdn.net/weixin_33834628/article/details/91430312) -就是TP95
    - [https://github.com/addthis/stream-lib/blob/master/src/main/java/com/clearspring/analytics/stream/quantile/TDigest.java](https://github.com/addthis/stream-lib/blob/master/src/main/java/com/clearspring/analytics/stream/quantile/TDigest.java)
    - [https://github.com/addthis/stream-lib/blob/master/src/main/java/com/clearspring/analytics/stream/cardinality/HyperLogLog.java](https://github.com/addthis/stream-lib/blob/master/src/main/java/com/clearspring/analytics/stream/cardinality/HyperLogLog.java)
    - []()
- [实时计算工具库 stream-lib 使用指南](https://blog.csdn.net/zjerryj/article/details/77628694)
    - HyperLogLog 唯一值计算  独立访客（UV）是网站的重要指标之一
    - BloomFilter 用于检测一个元素是否包含在集合中 特点是有一定几率误报（False Positive Probability, FPP），但绝不会漏报（False Negative）
    - CountMinSketch Top K 排名
    - T-Digest 分位数计算 TP95
    - obyCount 大数据算法-重复率计算 ICardinality
    - 等等
- [stram-lib官方-doc](https://www.javadoc.io/doc/com.clearspring.analytics/stream/2.9.5/index.html)
    
## [五个常用好用的数据结构(BloomFilter、Hyperloglog等)](https://blog.csdn.net/keyboard2000/article/details/91424889)
- [MinHash-是一种基于 Jaccard Index 相似度的算法，用于快速估计两个集合的相似度](https://blog.csdn.net/keyboard2000/article/details/91424889)
    - [https://github.com/ALShum/MinHashLSH， https://www.sanfoundry.com/java-program-implement-min-hash/](https://github.com/ALShum/MinHashLSH， https://www.sanfoundry.com/java-program-implement-min-hash/)  
    ```
    使用场景：
    
    去重。例如爬虫系统对于重复网页的去重处理。
    可以计算近似物品或相似的用户，也可以用户近似图像的搜索，将图像提取局部不变或全局特征后用minhash降维做最邻近搜索，就可以匹配到相同或轻微变化的图片。
    在数据挖掘领域中，minhash也可作为关联规则学习，用来辨别频繁共同出现的属性候选对，然后仅计算这些候选对的确切系数值，以确定哪些项目共同出现的频度低于一个给定的严格阈值。
    相似检索、推荐系统、聚类分析等
    ```
- [Count-Min Sketch-是一个概率数据结构，和BloomFilter的统计机制类似，用作统计数据流中事件的频率](https://blog.csdn.net/keyboard2000/article/details/91424889)
    - [https://github.com/addthis/stream-lib/blob/master/src/main/java/com/clearspring/analytics/stream/frequency/CountMinSketch.java](https://github.com/addthis/stream-lib/blob/master/src/main/java/com/clearspring/analytics/stream/frequency/CountMinSketch.java)
    ```
    Count-min Sketch 是一个概率数据结构，和BloomFilter的统计机制类似，用作统计数据流中事件的频率。如果使用HashMap来统计各个元素的出现频率，但由于不同的元素的个数可能非常大，以至于是个天文数字，要求的内存可能会非常大，从而不切实际。
  如果不需要太精确的计数，可以使用Count-min Sketch
    ```
- [t-Digest-中位数、95% 分位数](https://blog.csdn.net/keyboard2000/article/details/91424889)
    - [https://github.com/addthis/stream-lib](https://github.com/addthis/stream-lib)
    - [https://github.com/tdunning/t-digest](https://github.com/tdunning/t-digest)
    ```
    使用场景：实时快速的求出百亿流数据的百分位数。elasticSearch的percentiles 使用一个 TDigest 算法用于百分位近似统计。
    ```
- [分布式缓存击穿（布隆过滤器 Bloom Filter）](https://blog.csdn.net/wuxian90/article/details/81075432)
    - [大量数据去重：Bitmap和布隆过滤器(Bloom Filter)](https://blog.csdn.net/zdxiq000/article/details/57626464)
- []()

> 总结:以上几种算法都是通过牺牲准确性来提升时间与空间的利用效率的，在大量数据的场景有很大的应用价值。
> stream-lib是比较有名的java实时计算与基数统计工具库，有兴趣的可以翻下github： https://github.com/addthis/stream-lib

## 排序-基于Storm的在线算法
- [https://github.com/liuxiyang641/online-algorithm-storm](https://github.com/liuxiyang641/online-algorithm-storm)
- [https://github.com/yaozd/online-algorithm-storm](https://github.com/yaozd/online-algorithm-storm)
- 插入排序	插入排序-离线版	src/main/java/insertsort/InserSortMain.java
- Misra-Gries算法	最频繁元素查找算法	src/main/java/misragries/MisraGriesMain.java
    - [Misra-Gries 算法](https://www.cnblogs.com/super-zhang-828/p/7353217.html)
    > Misra-Gries算法输出的数据项并不一定是频繁项，但是频繁项一定在输出结果之中。
    >后一句便是问题的关键了，它表明Misra-Gries算法可以确保找到数据流中的频繁项。
- 感知器在线训练算法	--	src/main/java/perception/PerceptionMain.java
    - [感知机算法（Perceptron Learning Algorithm）](https://www.jianshu.com/p/fbfe531ec233)
    - [https://github.com/nsadawi/perceptron](https://github.com/nsadawi/perceptron)
      ```
      感知机（perceptron）是二类分类的线性分类模型，它的思想很简单，就是在一个二维空间中寻找一条直线将红点和蓝点分开（图1），
      类比到高维空间中，感知机模型尝试寻找一个超平面，将所有二元类别分开
      ```
