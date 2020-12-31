###　算法的时间复杂度
> 算法的时间复杂度，用来度量算法的运行时间，记作: T(n) = O(f(n))。它表示随着 输入大小n 的增大，算法执行需要的时间的增长速度可以用 f(n) 来描述。

### 参考：
- [各种数据结构的时间复杂度分析](https://blog.csdn.net/chao2016/article/details/82425317)
- [O(1), O(n), O(logn), O(nlogn) 的区别](https://blog.csdn.net/ted_cs/article/details/82881831)
- [各种数据结构的时间复杂度是多少？](https://cloud.tencent.com/developer/ask/112047)
- [算法复杂度分析，算法复杂度o(1), o(n), o(logn), o(nlogn) 时间复杂度On和空间复杂度O1是什么意思？](https://blog.csdn.net/lhq186/article/details/88031799)
- [算法复杂度分析](https://www.cnblogs.com/TangBiao/p/5856695.html) 推荐参考byArvin
- [拜托，面试别再问我时间复杂度了！！！](https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651961615&idx=1&sn=8711d52aa7bbd77db02eded67f6cc299) 58沈剑 架构师之路 推荐byArvin
- []()

### [复杂度分析的套路及常见的复杂度](https://www.cnblogs.com/tong-yuan/p/13376223.html)
```
常见的复杂度
上面我们说了，复杂度的计算就是计算与输入规模n的关系，所以，我们想想数学中关于n的函数就能得出常见的复杂度了，我绘制了一张表格：

与n的关系	英文释义	复杂度	示例
常数（不相关）	Constant	O(1)	数组按索引查找元素
对数相关	Logarithmic	O(logn)	二分查找
线性相关	Linear	O(n)	遍历数组的元素
超线性相关	Superlinear	O(nlogn)	归并排序、堆排序
多项式相关	Polynomial	O(n^c)	冒泡排序、插入排序、选择排序
指数相关	Exponential	O(c^n)	汉诺塔
阶乘相关	Factorial	O(n!)	行列式展开
n的n次方	无	O(n^n)	不知道有没有这种算法
在这张表中，复杂度是依次增加的，可以看到常数复杂度O(1)无疑是最好的，让我们用一张图来直观感受下：
```