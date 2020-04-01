## Java一个对象占用内存的大小
- [Java一个对象占用内存的大小](https://jingyan.baidu.com/article/cd4c297925f231756e6e600f.html)
- [对象内存计算神器](https://blog.csdn.net/linzhiqiang0316/article/details/94214255)

```
 * 点评：使用该第三方工具比较简单直接，
 主要依靠JVM本身环境、参数及CPU架构计算头信息，
 再依据数据类型的标准计算实例字段大小，计算速度很快，另外使用较方便。
 * 如果非要说这种方式有什么缺点的话，
 那就是这种方式计算所得的对象头大小是基于JVM声明规范的，
 并不是通过运行时内存地址计算而得，存在与实际大小不符的这种可能性。
```