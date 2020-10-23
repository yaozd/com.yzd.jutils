# EagerThreadPool
```
这种线程池机制的是这样的
先起线程，到最大值,再进队列
```
- [面试官：这道线程池场景题回答一下](https://www.sohu.com/a/391767502_355142)

## Dubbo的线程模型中可使用4种线程池
```  
   CachedThreadPool
   LimitedThreadPool
   FixedThreadPool
   EagerThreadPool
```
## 重要参考
- [https://github.com/fang-yan-peng/sq-grpc](https://github.com/fang-yan-peng/sq-grpc) 推荐参考
PS: sq-grpc应该也是参考了dubbo
```
1.
时间轮
2.
通过URL进行传参
3.
EagerThreadPool
这种线程池机制的是这样的
先起线程，到最大值,再进队列
```