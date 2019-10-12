##　CopyOnWrite
- CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
## 参考：
- [聊聊并发-Java中的Copy-On-Write容器](http://ifeve.com/java-copy-on-write/)
- [CopyOnWriteMap](http://ifeve.com/java-copy-on-write/)
- []()
- []()
- []()

### CopyOnWriteMap使用需要注意两件事情：
```
1. 减少扩容开销。根据实际需要，初始化CopyOnWriteMap的大小，避免写时CopyOnWriteMap扩容的开销。
2. 使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器的复制次数。如使用上面代码里的addBlackList方法。
```

### CopyOnWrite的应用场景
> CopyOnWrite并发容器用于读多写少的并发场景。比如白名单，黑名单，商品类目的访问和更新场景，假如我们有一个搜索网站，用户在这个网站的搜索框中，输入关键字搜索内容，但是某些关键字不允许被搜索。这些不能被搜索的关键字会被放在一个黑名单当中，黑名单每天晚上更新一次。当用户搜索时，会检查当前关键字在不在黑名单当中，如果在，则提示不能搜索。实现代码如下

### CopyOnWrite的缺点
> CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。所以在开发的时候需要注意一下
