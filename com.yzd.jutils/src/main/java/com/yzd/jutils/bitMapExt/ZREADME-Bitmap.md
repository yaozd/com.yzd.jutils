## 海量数据处理-bitmap
- [通过BitSet源码来理解BitMap算法](https://www.jianshu.com/p/0f653d2302a0)
- [使用bitmap进行大量数据的排序、判断存在与否](https://blog.csdn.net/qq_33240946/article/details/83929158)
- [大数据处理算法一：BitMap算法](https://blog.csdn.net/h348592532/article/details/45362661?from=singlemessage)
- []()
- []()

## bitmap-高级使用
- [sparsebitmap](https://github.com/lemire/sparsebitmap)-bitmap与int之间的转换
- []()
- []()
- []()

## 拓展
```
如要论述拓展，要么就是论述场景的高层次应用，要么就是论述此算法的不足之处，此处各提一个点：

① 不足：数据稀疏问题，比如三个元素(1,100,10000000)，则需要初始化的长度为 10000000，很不合理，此时可以使用 Roaring BitMap 算法来解决，而 Java 程序可以使用goolge的 **EWAHCompressedBitmap **来解决。

② 拓展：数据碰撞问题，比如上文提到的爬虫应用场景是将URL进行哈希运算，然后将hash值存入BitMap之中，但是不得不面临一个尴尬的情况，那就是哈希碰撞，而布隆算法（Bloom Filter）就可以解决这个问题，为什么是拓展呢？因为它是以 BitMap 为基础的排重算法。

原文地址：http://www.jetchen.cn/algorithm-bitmap-bitset/
```