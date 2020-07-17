## netty channel 状态都可以做什么操作？
- [netty channel 状态都可以做什么操作？](https://ask.csdn.net/questions/753582)
```
netty 有isActive，isOpen，isRegistered，isWritable，这四种状态有什么区别，有没有先后顺序，
在这四种状态都能对channel做什么操作比如close，write，flush等？？？
答：
有区别，分别是打开，注册，激活，可写。顺序是 open -> Registered -> Active -> Writable . 
在 isWritable 状态下才能 close，write，flush。 具体的可以看下NIO 的流程
```