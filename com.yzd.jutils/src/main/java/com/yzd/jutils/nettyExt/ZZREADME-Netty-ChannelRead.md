## channelRead()和channelReadComplete() 方法的区别是什么？
- [channelRead()和channelReadComplete() 方法的区别是什么？](https://segmentfault.com/q/1010000018753423)
```
channelRead（）：触发是在当前一个TCP请求读取的时候,一次TCP的传输可能包含多个请求，如：同一个channel执行pipeline的模式
channelReadComplete():触发是在当前一个TCP请求完全读取结束的时候，进行触发。
```