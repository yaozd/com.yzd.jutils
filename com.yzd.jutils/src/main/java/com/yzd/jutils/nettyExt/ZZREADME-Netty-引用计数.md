## 《Netty官方文档》引用计数对象
- [《Netty官方文档》引用计数对象](https://ifeve.com/reference-counted-objects/)
```
谁来销毁
一般的原则是，最后访问引用计数对象的部分负责对象的销毁。更具体地来说：
如果一个[发送]组件要传递一个引用计数对象到另一个[接收]组件，发送组件通常不需要 负责去销毁对象，而是将这个销毁的任务推延到接收组件
如果一个组件消费了一个引用计数对象，并且不知道谁会再访问它（例如，不会再将引用 发送到另一个组件），那么，这个组件负责销毁工作 这里有一个简单的示例：

```
## 子缓冲区（Derived buffers)
```
调用ByteBuf.duplicate(),ByteBuf.slice()和ByteBuf.order(ByteOrder)三个方法， 会创建一个子缓冲区，子缓冲区共享父缓冲区的内存区域。子缓冲区没有自己的引用计数，而是 共享父缓冲区的引用计数。
ByteBuf parent = ctx.alloc().directBuffer();
ByteBuf derived = parent.duplicate();

// Creating a derived buffer does not increase the reference count.
assert parent.refCnt() == 1;
assert derived.refCnt() == 1;

但是，调用ByteBuf.copy()和ByteBuf.readBytes(int)创建的并不是子缓冲区，返回的 ByteBuf缓冲区是需要被释放的。 需要注意，父缓冲区和它的子缓冲区共享引用计数，创建子缓冲区并不会增加引用计数。 因此，当你将子缓冲区传到应用中的其他组件，必须先调用retain()。

ByteBuf parent = ctx.alloc().directBuffer(512);
parent.writeBytes(...);

try {
 while (parent.isReadable(16)) {
 ByteBuf derived = parent.readSlice(16);
 derived.retain();
 process(derived);
 }
} finally {
 parent.release();
}
...

public void process(ByteBuf buf) {
 ...
 buf.release();
}
```
