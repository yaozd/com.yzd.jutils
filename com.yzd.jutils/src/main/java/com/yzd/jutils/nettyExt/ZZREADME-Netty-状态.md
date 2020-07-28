## netty channel 状态都可以做什么操作？
- [netty channel 状态都可以做什么操作？](https://ask.csdn.net/questions/753582)
```
netty 有isActive，isOpen，isRegistered，isWritable，这四种状态有什么区别，有没有先后顺序，
在这四种状态都能对channel做什么操作比如close，write，flush等？？？
答：
有区别，分别是打开，注册，激活，可写。顺序是 open -> Registered -> Active -> Writable . 
在 isWritable 状态下才能 close，write，flush。 具体的可以看下NIO 的流程
```
- [Channel状态转换](https://blog.csdn.net/zxhoo/article/details/17964353)
```
代码分析的很复杂，但结论很简单：被Bootstrap引导的NioSocketChannel在构造好之后就进入了open状态，之后通过把自己注册进EventLoop进入registered状态，接着连接服务器进入active状态。
```