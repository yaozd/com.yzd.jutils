## [Netty之有效规避内存泄漏](https://www.cnblogs.com/549294286/p/5168454.html)
```
内存泄漏检测
-Dio.netty.leakDetectionLevel=paranoid
//
报告有泄漏的发生，提示你用-D参数，把防漏等级从默认的simple升到advanced，就能具体看到被泄漏的ByteBuf被创建和访问的地方。

禁用（DISABLED） - 完全禁止泄露检测，省点消耗。
简单（SIMPLE） - 默认等级，告诉我们取样的1%的ByteBuf是否发生了泄露，但总共一次只打印一次，看不到就没有了。
高级（ADVANCED） - 告诉我们取样的1%的ByteBuf发生泄露的地方。每种类型的泄漏（创建的地方与访问路径一致）只打印一次。对性能有影响。
偏执（PARANOID） - 跟高级选项类似，但此选项检测所有ByteBuf，而不仅仅是取样的那1%。对性能有绝大的影响。
```